<template>
  <div id="word-cloud" style="height: 100%">
  </div>
</template>

<script>

const d3 = require('d3')


export default {
  name: 'WordCloud',
  data(){
    return{
      words: [
        { text: "명배우", size: 300 },
        { text: "존잼", size: 264 },
        { text: "좋았다", size: 220 },
        { text: "슬픔", size: 111 },
        { text: "허무", size: 325 },
        { text: "가족", size: 101 },
        { text: '가성비', size: 50 },
        { text: '행복', size: 78  },
        { text: '댄스타임', size: 40 },
        { text: '최악', size: 12 },
      ],
      width : 1000,
      height : 700
    }
  },

  mounted(){
    this.getLayout()
  },

  methods:{
    getLayout(){
      const cloud = require('d3-cloud')
      const wordScale = d3.scaleLinear().domain([0, 400]).range([0, 200]).clamp(true)
      cloud()
        .words(this.words)
        .rotate(function(d){ return d.size > 200 ? 0 : 90})
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
        .attr('width', this.width)
        .attr('height', this.height)
        .style('background', 'white')
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

</style>