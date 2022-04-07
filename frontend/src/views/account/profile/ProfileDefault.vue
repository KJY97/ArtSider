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
          style="height: 100%;  max-height: 800px;"
        >
          <v-col
            v-for="(poster, i) in preferencePosters"
            :key="i"
            class="col-3 py-4"
            style="height: 80%; "
          >

            <v-btn
              icon
              @click="() => { dialog = true, clickedId = poster.show.id}"
            >
              <v-icon>
                mdi-close-box-outline
              </v-icon>
            </v-btn>
            <v-card
              max-width="180"
              max-height="240"
              class="mx-auto"
              style="background-color: gray;"
              @click="$router.push({name: 'ShowDetail', params: {showId: poster.show.id}})"
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
          <v-dialog
            :value="dialog"
            @click:outside="() => {dialog = false, clickedId = null}"
            max-width="500"
            min-width="450"
          >
              <v-card style="background-color:rgba(0,0,0,0.8);"> 
                <v-card-title class="text-h5" style="color:white;">
                  <strong> 정말 관심 목록에서 삭제하시겠습니까? </strong>
                </v-card-title>
                <v-card-text style="color:white;">
                  아트사이더의 추천 항목 서비스에 영향을 줄 수 있습니다. 
                </v-card-text>
                <v-spacer></v-spacer>
                <v-card-actions class="d-flex justify-space-around">
                  <v-btn
                    color="green darken-1"
                    text
                    @click="() => { deletePreference(clickedId), dialog = false }"
                  >
                    삭제할래요
                  </v-btn>
                  <v-btn
                    color="green darken-1"
                    text
                    @click="() => {dialog = false, clickedId = null}"
                  >
                    안할래요
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
        </v-row>
      </v-container>
    </div>
    <div 
      class="history"
    >
      <div class="d-flex align-center">
        <v-icon size="48px" color="green" > mdi-cart </v-icon>
        <h1>  조회한 공연 </h1>
      </div>
      <v-container
        class="history-contents-box"
      >
        <v-row
          style="height: 100%; max-height: 800px;"
        >
          <v-col
            v-for="(poster, i) in historyPosters"
            :key="i"
            class="col-3 py-4"
            style="height: 80%;"
          >
            <v-card
              max-width="67.5%"
              max-height="90%"
              class="history-content mx-auto"
              style="background-color:gray;"
              @click="$router.push({name: 'ShowDetail', params: {showId: poster.id}})"
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
      dialog: false,
      clickedId : null
    }
  },

  methods: {
    deletePreference(clickedId){
      this.$store.dispatch('deletePreference', {id: clickedId})
      .then(() => {
        this.clickedId = null
        this.$router.go()
      })
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
  height: 80vh;
  
}

.preference .preference-contents-box, .history .history-contents-box{
  width: 100%;
  height: 80%;
  background-color: #FFF0E2;
  overflow-y: auto;
}
</style>