<template>
   <div class="my-contents-wrapper mt-5">
    <div
      class="preference"
    >
      <h1> 관심 콘텐츠 </h1>
      <v-container
        class="preference-contents-box"

      >
        <!-- <v-img
          width="300"
          height="400"
          v-for="(poster, i) in posters"
          :key="i"
        >
        </v-img> -->

        <v-row
          style="height: 100%;"
        >
          <v-col
            v-for="(poster, i) in posters"
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
            v-for="(poster, i) in posters"
            :key="i"
            class="col-3 py-4"
            style="height: 80%; "
          >
            <v-card
              width="70%"
              height="100%"
              class="history-content mx-auto"
              style="background-color:gray;"
            >
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
      posters: ['poster1', 'poster2', 'poster3', 'poster4', 'poster5', 'poster6', 'poster7']
    }
  },
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