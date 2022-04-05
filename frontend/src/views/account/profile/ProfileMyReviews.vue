<template>
  <v-row
    class="mt-5"
  >
    <v-col
      cols="6"
    >
      <h1> 내가 작성한 리뷰</h1>
      <div class="my-reviews-box"  style="margin-top:20px;">
        <div class="my-review"
          v-for="(review, i) in myReviews"
          :key="i"
        >
          <v-container
            @click="$router.push({name: 'ShowDetail', params: {showId : review.showId}})"
            style="border: 1px solid rgba(0, 0, 0, .3); border-radius:20px; cursor: pointer;"
          >
            <span class="opacity-text">평점 : {{ review.rating }} | </span>
            <div>
              <br>
              <strong> {{review.title}} </strong> 
            </div>
            <br>
            <span> {{review.contents}} </span>
            <br>
            <span class="opacity-text">작성 시간 :  | </span>
          </v-container>
        </div>
      </div>
    </v-col>

    <v-col
      cols="6"
    >
      <h1> 별점 분포 그래프 </h1>
      <div 
        class="chart-box"
      >
        <BarChart v-if="chartRender" :score-list="myScores" />
      </div>
    </v-col>
    <v-col
      cols="12"
    >
      <h1> 관심 공연 해쉬맵 </h1>
      <div
        class="word-map-wrapper"
        style="background-color: gray; height: 80%;"
      >
        <WordCloud :words="userTagsCloud" />
      </div>
    </v-col>
  </v-row>
</template>

<script>
import BarChart from './components/BarChart'
import WordCloud from './components/WordCloud'

export default {
  name: 'ProfileMyReviews',
  components:{
    BarChart,
    WordCloud
  },
  data(){
    return{
      myReviews : [],
      myScores: [],
      myTags : [],

      chartRender : false
    }
  },

  computed:{
    userTagsCloud(){
      const newWords = []
      const wordsObject = this.myTags
      const keysList = Object.keys(wordsObject)
      for(let i = 0; i < keysList.length; i++){
        const key = keysList[i]
        const value = wordsObject[key]
        console.log('단어: ', key, '갯수: ', value)
        newWords.push({text: key, size: value })
      }
      return newWords 
    }

  },

  watch:{
    myScores: function(){
      this.chartRender = true
    }
  },

  methods:{
    getData(scoreList){
      const scoresData = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
      scoreList.forEach((elem) => {
        scoresData[elem.rating - 1] = elem.cnt
      })
      return scoresData
    }
  },

  created(){
    this.$store.dispatch('getUserReviews')
    .then((res) => {
      console.log(res.data)
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
            this.myTags = res.items
          })
        })
    })
  }

}
</script>

<style>

.my-reviews-box, .chart-box{
  width: 100%;
  height: 70%;
}

.my-reviews-box{
  height: 80%;
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

</style>