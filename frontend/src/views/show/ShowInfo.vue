<template>
  <div>
    <p>내정보</p>
    <div v-for="(image,idx) in subPosterPath" :key="idx">
      <v-img :src="image"></v-img>
    </div>
    줄거리 : {{ this.story }}
    출연진 : {{ this.cast }}
    제작진 : {{ this.producer }}
  </div>
</template>

<script>
export default {
  name : 'ShowInfo',
  props: {
    showId: Object,
    subPosterPath: Object,
    story: Object, 
    cast: Object, 
    producer: Object 
  },
}
</script>

<style>

</style>