<template>
  <div id="word-cloud" class="word-cloud-wrapper">
  </div>
</template>

<script>

const d3 = require('d3')


export default {
  name: 'WordCloud',
  // props:{
  //   words: Object,
  // },

  data(){
    return{
      words: [
        { text: "명배우", size: 3 },
        { text: "존잼", size: 2 },
        { text: "좋았다", size: 2 },
        { text: "슬픔", size: 1 },
        { text: "허무", size: 3 },
        { text: "가족", size: 11 },
        { text: '가성비', size: 5 },
        { text: '행복', size: 7  },
        { text: '댄스타임', size: 4 },
        { text: '최악', size: 12 },
        { text: "ㅎㅎㅎㅎ", size: 3 },
        { text: "테스트", size: 2 },
        { text: "짱", size: 2 },
        { text: "힘들다", size: 1 },
        { text: "배고프다", size: 3 },
        { text: "노잼", size: 11 },
        { text: '굳', size: 5 },
        { text: '해피', size: 7  },
        { text: '후', size: 4 },
        { text: '따라딴', size: 12 },
        { text: '이렇게', size: 12 },
        { text: "태그를", size: 3 },
        { text: "시각화", size: 2 },
        { text: "삼성", size: 2 },
        { text: "복수", size: 1 },
        { text: "희망적", size: 3 },
        { text: "gg", size: 11 },
        { text: '짠', size: 5 },
        { text: '별로', size: 7  },
        { text: 'ssafy', size: 4 },
        { text: '소프트웨어', size: 12 },
      ],
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

    this.getLayout()
  },

  methods:{
    getLayout(){
      const cloud = require('d3-cloud')
      const wordScale = d3.scaleLinear().domain([0, 20]).range([0, 250]).clamp(true)
      cloud()
        .words(this.words)
        .rotate(function(d){ return d.size > 5 ? 0 : 90})
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