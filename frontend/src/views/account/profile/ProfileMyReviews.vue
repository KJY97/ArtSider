<template>
  <div>
    <v-row
      class="mt-5"
      style="height: 80vh; min-height: 400px; max-height: 800px;"
    >
      <v-col
        cols="6"
        style="height: 100%;"

      >
        <div class="d-flex align-center">
          <v-icon size="48" > mdi-pencil-box-multiple-outline </v-icon>  <h1> 작성한 후기</h1>
        </div>
        <div class="my-reviews-box"  style="margin-top:20px;">
          <div class="my-review mb-5"
            v-for="(review, i) in myReviews"
            :key="i"
          >
            <v-container
              @click="$router.push({name: 'ShowDetail', params: {showId : review.showId}})"
              style="border: 1px solid rgba(0, 0, 0, .3); border-radius:20px; cursor: pointer;"
            > 
              <span class="opacity-text"> 제목: {{ review.showName }} </span>
              <br>
              <span class="opacity-text">평점 : {{ review.rating }} | </span>
              <span class="opacity-text">작성 시간 :  {{ review.createDate.slice(0,10) }} </span>
              <div>
                <br>
                <strong> {{review.title}} </strong> 
              </div>
              <br>
              <span> {{review.contents}} </span>
              <br>
            </v-container>
          </div>
        </div>
      </v-col>

      <v-col
        cols="6"
        style="height: 100%;"
      >

        <div class="d-flex align-center">
          <v-icon size="48"  color="#F59E9E"> mdi-chart-bar </v-icon>
          <h1> 별점 그래프 </h1>
        </div>
        <div 
          class="chart-box"
        >
          <BarChart v-if="chartRender" :score-list="myScores" />
        </div>
      </v-col>
    </v-row>

    <v-row
      style="height: 70vh; min-height: 400px;"
    >
      <v-col
        cols="12"
      >
        <div class="d-flex align-center">
          <v-icon color="#46D2D2" size="48"> mdi-cloud-outline </v-icon>
          <h1> 나의 태그맵 </h1>
        </div>
        <div
          class="word-map-wrapper"
          style="height: 100%;"
        >
          <div 
            id="word-cloud" class="word-cloud-wrapper">
          </div>
        </div>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import BarChart from './components/BarChart'


const d3 = require('d3')

export default {
  name: 'ProfileMyReviews',
  components:{
    BarChart,
  },
  data(){
    return{
      myReviews : [],
      myScores: [],
      loading: true,

      chartRender : false,

      
      words : [],
      width : null,
      height : null
    }
  },

  methods:{
    getData(scoreList){
      const scoresData = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
      scoreList.forEach((elem) => {
        scoresData[elem.rating - 1] = elem.cnt
      })
      return scoresData
    },

    getLayout(){
      const cloud = require('d3-cloud')
      const wordScale = d3.scaleLinear().domain([0, 10]).range([40, 100]).clamp(true)
      cloud()
        .words(this.words)
        .rotate(function(){ return 0 })
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
          .style('fill', function(){
            const r = parseInt(Math.random() * 255);
            const g = parseInt(Math.random() * 255);
            const b = parseInt(Math.random() * 255);
            return `rgb(${r}, ${g}, ${b})`;
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
    },


    getFilterWords(myTags){
      if(myTags.length !== 0){
        const newWords = []
        const wordsObject = myTags
        const keysList = Object.keys(wordsObject)
        for(let i = 0; i < keysList.length; i++){
          const key = keysList[i]
          const value = wordsObject[key]
          const text = value.tag
          const cnt = value.cnt
          newWords.push({text: text, size: cnt })
        }
        return newWords 
      }
    },
  },


  created(){
    this.$store.dispatch('getUserReviews')
    .then((res) => {
      this.myReviews = res.data.items
    })
    .then(() => {
        this.$store.dispatch('getUserScores')
        .then((res) => {
          this.myScores = this.getData(res.data.items)
        })
        .then(() => {
            this.$store.dispatch('getUserTags')
            .then((res) => {
              this.words= this.getFilterWords(res.data.items)
            })       
        })
      })
    },

    mounted(){
      const wrapper = document.querySelector('#word-cloud')
      const wrapperHeight = wrapper.clientHeight
      const wrapperWidth = wrapper.clientWidth
      this.width = wrapperWidth
      this.height = wrapperHeight
  },

  watch: {
    myScores: function(){
      this.chartRender = true
    },

    words : function(){
      if(this.words.length !== 0){
        this.getLayout()
      }
    }
  },

}
</script>

<style>

.my-reviews-box, .chart-box{
  width: 100%;
  height: 80%;
}

.my-reviews-box{
  overflow-y: auto;
}

/* .my-reviews-box .my-review{
  margin-bottom: 30px;
  height: 30%;
  background-color: #FFF0E2;
} */

.opacity-text {
    opacity: 0.6;
  }

.word-cloud-wrapper{
    position: relative;
    height: 100%;
    vertical-align: top;
    overflow: hidden;
}


</style>