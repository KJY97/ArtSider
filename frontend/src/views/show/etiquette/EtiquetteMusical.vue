<template>
<div>
  <div class="etiquette-detail">
    <div class="show-image col-3">
      <v-img
        height="500px"
        width="500px"
        src="https://d3kb3kbkoieiep.cloudfront.net/upFiles/StageTalkV2/Wicked/20210401/2021040111082381a53b8da93b4dc6b4be19eec440d19b.jpg"
        :elevation="3" 
      ></v-img>
    </div>
    <v-container
      class="fill-height"
      style="min-height: 134px"
    >      
      <div class="category-btn"> 
        <v-row>
            <v-col cols="4">
              <v-img
                src="https://cdn2.iconfinder.com/data/icons/coronavirus-covid-19-7/128/coronavirus_covid-19_disease_pandemic_epidemic_injury-256.png"
                height="125"
                contain
                @click="showEtiquette(1)"
              ></v-img>
            </v-col>
          <router-view></router-view>
          <v-col cols="4">
            <v-img
              src="https://cdn1.iconfinder.com/data/icons/hand-gestures-color/128/hand-clap-y-256.png"
              max-height="125"
              contain
              @click="showEtiquette(2)"
            ></v-img>
          </v-col>
          <v-col cols="4">
            <v-img
              src="https://cdn2.iconfinder.com/data/icons/prohibitions/105/05-512.png"
              max-height="125"
              contain
              @click="showEtiquette(3)"
            ></v-img>
          </v-col>
          <v-col cols="4" class="btnbtn">
            <v-img
              src="https://cdn4.iconfinder.com/data/icons/prohibited-red-signs/122/Prohibition_sign_044-256.png"
              max-height="125"
              contain
              @click="showEtiquette(4)"
            ></v-img>
          </v-col>
          <v-col cols="4">
            <v-img
              src="https://cdn2.iconfinder.com/data/icons/prohibited-forbidden-signs/100/Prohibited-23-256.png"
              max-height="125"
              contain
              @click="showEtiquette(5)"
            ></v-img>
          </v-col>
          <v-col cols="4">
            <v-img
              src="https://cdn2.iconfinder.com/data/icons/basic-flat-icon-set/128/clock-256.png"
              max-height="125"
              contain
              @click="showEtiquette(6)"
            ></v-img>
          </v-col>
        </v-row>
        <v-row class="etiquette-description mt-4">
          <v-col
            cols="6"
            sm="10"
          >
            <v-card
              class="pa-4"
              outlined
              style="background-color: pink;"
              tile
              :elevation="3" 
            >
              <div>
                <p v-if="num==1"><Covid19 /></p>
                <p v-else-if="num==2"><Clap :clap="4"/></p>
                <p v-else-if="num==3"><Camera /></p>
                <p v-else-if="num==4"><Phone /></p>
                <p v-else-if="num==5"><Talk /></p>
                <p v-else-if="num==6"><Time /></p>
              </div>
            </v-card>
          </v-col>
        </v-row>
      </div>
    </v-container>
  </div>
</div>
  
</template>

<script>
import Covid19 from './etiquetteDetail/Covid19.vue'
import Clap from './etiquetteDetail/Clap.vue'
import Camera from './etiquetteDetail/Camera.vue'
import Phone from './etiquetteDetail/Phone.vue'
import Talk from './etiquetteDetail/Talk.vue'
import Time from './etiquetteDetail/Time.vue'

export default {
  name: 'EtiquetteMusical',
  components: {
    Covid19,
    Clap,
    Camera,
    Phone,
    Talk,
    Time
  },
  data() {
    return{
      num : 1
    }
  },
  methods: {
    showEtiquette : function (num) {
      if (num == 1) {
        this.num = 1
      } else if (num == 2) {
        this.num = 2
      } else if (num == 3) {
        this.num = 3
      } else if (num == 4) {
        this.num = 4
      } else if (num == 5) {
        this.num = 5
      } else if (num == 6) {
        this.num = 6
      }    
    }
  }
}
</script>

<style>
.show-category {
  display: flex;
  justify-content: space-evenly;
}

.show-image {
  margin-left: 10rem;
}

.category-btn {
  margin-right: 8rem;
  /* height: 10px; */
}

.btnbtn {
  height: 10px;
}

.etiquette-title {
  display: flex;
  justify-content: center;
}

.etiquette-detail {
  display: flex;
  justify-content: center;
}

.etiquette-description {
  justify-content: center;
}
</style>