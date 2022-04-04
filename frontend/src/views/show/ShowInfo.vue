<template>
  <div>
    <br>
    <div v-for="(image,idx) in showDetailImg" :key="idx">
      <v-img :src="image.imgUrl"></v-img>
    </div>
  </div>
</template>

<script>
export default {
  name : 'ShowInfo',
  props: {
    showDetailImg: Array,
  },
}
</script>

<style>

</style>