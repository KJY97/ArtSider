<template>
   <div class="my-contents-wrapper mt-5">
    <div
      class="preference"
    >
      <h1> 관심 콘텐츠 </h1>
      <v-container
        class="preference-contents-box"
      >
        <v-row
          style="height: 100%;"
        >
          <v-col
            v-for="(poster, i) in preferencePosters"
            :key="i"
            class="col-3 py-4"
            style="height: 80%;"
          >
            <v-card
              width="70%"
              height="100%"
              style="background-color: gray;"
              class="preference-content  mx-auto"
            >
              <v-img
                :src="poster.posterPath"
                :aspect-ratio="3/4"
                width="100%"
                height="100%"
              >

              </v-img>

            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </div>
    <div 
      class="history"
    >
      <h1> 내가 조회한 콘텐츠 목록 </h1>
      <v-container
        class="history-contents-box"
      >
        <v-row
          style="height: 100%;"
        >
          <v-col
            v-for="(poster, i) in historyPosters"
            :key="i"
            class="col-3 py-4"
            style="height: 80%; "
          >
            <v-card
              max-width="180"
              max-height="240"
              class="history-content mx-auto"
              style="background-color:gray;"
            >
              <v-img
                :src="poster.posterPath"
                :aspect-ratio="3/4"
                width="100%"
                height="100%"
              >
              </v-img>
            </v-card>
          </v-col>
        </v-row>
        
      </v-container>
    </div>
    
  </div>
</template>

<script>
export default {
  name: 'ProfileDefault',
  data(){
    return{
      preferencePosters : [],
      historyPosters : [],
    }
  },

  created(){
    this.$store.dispatch('getMyPreference')
    .then((res) => {
        this.preferencePosters = res.data.items
      })
      .catch((err) => {
        console.log(err)
      })



    this.$store.dispatch('getMyHistory')
    .then((res) => {
      this.historyPosters = res.data.items
    })
  }
}
</script>

<style>
.my-contents-wrapper{
  width: 100%;
  height: 100%;
}
.preference, .history{
  width: 100%;
  height: 80%;
  
}

.preference .preference-contents-box, .history .history-contents-box{
  width: 100%;
  height: 80%;
  background-color: #FFF0E2;
  overflow-y: auto;
}
</style>