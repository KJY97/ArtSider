<template>
  <div class="d-flex justify-center align-center" style="height:80vh; width:100%">
    <v-progress-circular
      :size="80"
      :width="8"
      indeterminate
      color="primary"
    ></v-progress-circular>
  </div>
</template>

<script>
export default {
  name: 'Loading',
}
</script>

<style>

</style>