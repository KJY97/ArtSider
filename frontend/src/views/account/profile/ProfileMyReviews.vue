<template>
  <v-row
    class="mt-5"
    style="height: 100%;"
  >
    <v-col
      cols="6"
      style="height: 50%;"
    >
      <h1> 내가 작성한 리뷰</h1>
      <div class="my-reviews-box">
        <div class="my-review"
          v-for="(review, i) in reviews"
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
      style="height: 50%;"
    >
      <h1> 별점 분포 그래프 </h1>
      <div 
        class="chart-box"
      >
        <BarChart />
      </div>
    </v-col>
    <v-col
      cols="12"
      style="height: 70%;"
    >
      <h1> 관심 공연 해쉬맵 </h1>
      <div
        class="word-map-wrapper"
        style="background-color: gray; height: 80%;"
      >
        <WordCloud />
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
      reviews: ['review1', 'review2', 'review3'],

    }
  },

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