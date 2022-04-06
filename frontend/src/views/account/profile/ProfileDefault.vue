<template>
   <div class="my-contents-wrapper mt-5">
    <div
      class="preference"
    >
      <div class="d-flex align-center">
        <v-icon size="48px" color="yellow"> mdi-star-check </v-icon>
        <h1>  관심 공연 </h1>  
      </div>
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
            style="height: 80%; "
          >
            <v-card
              max-width="180"
              max-height="240"
              class="mx-auto"
              style="background-color: gray;"
            >
              <v-img
                :src="poster.show.posterPath"
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
      <div class="d-flex align-center">
        <v-icon size="48px" color="green" > mdi-cart </v-icon>
        <h1 >  조회한 공연 </h1>
      </div>
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
      console.log(res.data.items)
      this.preferencePosters = res.data.items 
      this.$store.dispatch('getMyHistory')
      .then((res) => {
        this.historyPosters = res.data.items
      })
    })
    .catch((err) => {
      console.log(err)
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