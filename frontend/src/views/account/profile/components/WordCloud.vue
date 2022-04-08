<template>
  <div 
    id="word-cloud" class="word-cloud-wrapper">
  </div>
</template>

<script>

const d3 = require('d3')


export default {
  name: 'WordCloud',


  data(){
    return{

      words : [],
      width : null,
      height : null
    }
  },

  mounted(){
    const wrapper = document.querySelector('#word-cloud')
    const wrapperHeight = wrapper.clientHeight
    const wrapperWidth = wrapper.clientWidth
    this.width = wrapperWidth
    this.height = wrapperHeight
    this.words = this.$store.state.userTagsCloud
    console.log('클라우드 태그', this.words)

    this.getLayout()

  },

  methods:{
    getLayout(){
      const cloud = require('d3-cloud')
      const wordScale = d3.scaleLinear().domain([0, 5]).range([0, 100]).clamp(true)
      cloud()
        .words(this.words)
        .rotate(function(d){ return d.size >= 1 ? 0 : 90})
        .size([this.width, this.height])
        .font('Impact')
        .fontSize(function(d){
          return wordScale(d.size)
        })
        .on('end', this.draw)
        .start()
    },

    draw(words){
     const cloud =  d3.select('#word-cloud')
        .append('svg')
        .attr("preserveAspectRatio", "xMinYMin meet")
        .attr("viewBox", `0 0 ${this.width} ${this.height}`)
        .style('background', '#FFF0E2')
        .append('g')
        .attr('transform', 'translate(' + this.width / 2 + ',' + this.height / 2 + ')')
        .selectAll('text')
        .data(words)
        .enter()
        .append('text')
        .style('fill', function(d){
          if(d.size < 100 ){ return '#626208'}
          else if(d.size < 200 ) { return '#DD9933'}
          else if(d.size < 300) { return  '#1E73BE'}
          else if(d.size < 400 ){return '#DD3333'}
        })
        .style('fill-opacity', 5)
        .style('font-size', 1)
        .text((d) => d.text)
        .style('font-family', 'impact')
        .attr('text-anchor', 'middle')

      cloud
        .transition()
        .duration(600)
        .style('font-size', function(d){
          return d.size + 'px'
        })    
        .attr('transform', (d) => {
          return 'translate(' + [d.x, d.y] + ')rotate(' + d.rotate + ')'
        })
        .style('fill-opacity', 1)
    }

  }
}
</script>

<style>
.word-cloud-wrapper{
    position: relative;
    height: 100%;
    vertical-align: top;
    overflow: hidden;
}



</style>