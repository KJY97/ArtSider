<template>
  <div>
    <p>기대평</p>
  </div>
</template>

<script>
import axios from'axios'

export default {
  name: 'ShowExpectations',
  props: {
    showId: Object    
  },
  data: function() {
    return{
      showList: [],
    }
  },
  methods: {
    // 기대평 목록 조회
    getShowExpectations: function(showId) {
      axios({
        method: 'get',
        url: `http://127.0.0.1:8000/api/show/expectations/${showId}`,
      })
      .then(res => {
        console.log(res)
      })
    },
    // 기대평 생성
    createShowExpectations: function(showId) {
      axios({
        method: 'post',
        url: `http://127.0.0.1:8000/api/show/expectations/${showId}`,
        headers: this.setToken(),
        data: {
          
        },
      })
      .then(res => {
        console.log(res)
      })
    },
    // 기대평 제거
    deleteShowExpectations: function(expectationId) {
      axios({ 
        method: 'post',
        url: `http://127.0.0.1:8000/api/show/expectations/${expectationId}`,
        headers: this.setToken()
      })
      .then(() => {
        console.log('삭제 성공')
      })
    }

  },
  created: function () {
    this.showId = this.$route.params.showId
    this.getShowExpectations(this.showId)
  }
}
</script>

<style>

</style>