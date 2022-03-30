<template>
  <v-row
    class="mt-5"
  >
    <v-col
      cols="6"
    >
      <h1> 내가 작성한 리뷰</h1>
      <div class="my-reviews-box">
        <div class="my-review"
          v-for="(review, i) in userReviews"
          :key="i"
        >
          <p>
            {{ review }}
          </p>
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
        <BarChart :score-list="userScores" />
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
    }
  },

  computed:{
    userReviews(){
      return this.$store.getters.userReviews
    },

    userScores(){
      return this.$store.getters.userScores
    },

    userTagsCloud(){
      const newWords = []
      const wordsObject = this.$store.getters.userTagsCloud
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

  created(){
    this.$store.dispatch('getUserReviews')
    this.$store.dispatch('getUserScores')
    this.$store.dispatch('getUserTags')

  }

}
</script>

<style>

.my-reviews-box, .chart-box{
  width: 100%;
  height: 70%;
}

.my-reviews-box{
  overflow-y: auto;
}

.my-reviews-box .my-review{
  margin-bottom: 30px;
  height: 30%;
  background-color: #FFF0E2;
}

</style>