<template>
<div>
  <h1 class="etiquette-title mt-4 mb-10">공연 에티켓</h1>
  <div class="etiquette-category">
    <div class="show-category mb-8">
      <v-btn 
        :class="{'is-state': num == '1' }"
        @click="go(1)"
        text
        x-large
      >클래식</v-btn>
      <v-btn
        text
        @click="go(2)"
        x-large
        :class="{'is-state': num == '2' }" 
      >국악</v-btn>
      <v-btn
        text
        @click="go(3)"
        x-large
        :class="{'is-state': num == '3' }"
      >뮤지컬</v-btn>
      <v-btn
        text
        @click="go(4)"
        x-large
        :class="{'is-state': num == '4' }"
      >오페라</v-btn>
      <v-btn
        text
        @click="go(5)"
        x-large
        :class="{'is-state': num == '5' }"
      >연극</v-btn>
    </div>
  </div>
  <div>
    <Etiquette-classic 
    v-if="num == 1" 
    />
  </div>

  <div>
    <Etiquette-k-traditional-music 
    v-if="num == 2"
    />
  </div>

  <div>
    <Etiquette-musical 
    v-if="num == 3"
    />
  </div>

  <div>
    <Etiquette-opera 
    v-if="num == 4"
    />
  </div>

  <div>
    <Etiquette-theatre 
    v-if="num == 5"
    />
  </div>
</div>
</template>

<script>
import EtiquetteClassic from '../show/etiquette/EtiquetteClassic.vue'
import EtiquetteKTraditionalMusic from '../show/etiquette/EtiquetteKTraditionalMusic.vue'
import EtiquetteMusical from '../show/etiquette/EtiquetteMusical.vue'
import EtiquetteOpera from '../show/etiquette/EtiquetteOpera.vue'
import EtiquetteTheatre from '../show/etiquette/EtiquetteTheatre.vue'


export default {
  name: 'Etiquette',
  components: {
    EtiquetteClassic,
    EtiquetteKTraditionalMusic,
    EtiquetteMusical,
    EtiquetteOpera,
    EtiquetteTheatre
  },
  data() {
    return {
      num : 1
    }
  },
  methods: {
    go : function (num) {
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
      }    
    }
  }
}
</script>

<style>
.show-category {
  display: flex;
  justify-content: space-evenly;
  font-size: 3rem;
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

.is-state {
  background-color: rgba(255, 140, 0, 0.7);
  /* color: white; */
  /* text-decoration-color: white; */
}
</style>