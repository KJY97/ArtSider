<template>
<div>
  <div v-if="clap==1">
    <pre class="clap-detail">
      연극의 경우 기본적으로 한 막이 끝나면 박수를 칩니다.
      관객의 박수는 공연자에게 큰 힘이 됩니다.
    </pre>
  </div>
  <div v-if="clap==2">
    <pre class="clap-detail">
      음악공연의 경우 기본적으로 1곡이 끝나면 박수를 칩니다.
      교향곡, 협주곡, 소나타의 경우, 모든 악장이 끝난 후에 박수를 쳐야 합니다. 
      잘 모르겠다면 지휘자나 연주자가 객석을 향해 인사할 때 박수를 치면 됩니다.
      관객의 박수는 공연자에게 큰 힘이 됩니다.
    </pre>
  </div>

  <div v-if="clap==3">
    <pre class="clap-detail">
      음악공연의 경우 기본적으로 1곡이 끝나면 박수를 칩니다.
      판소리, 사물놀이 등의 경우 ‘얼쑤’, ‘좋지’, ’얼씨구’ 등의 추임새와 박수를 같이 친다면 
      분위기가 더 좋아질 것입니다.
      출연자가 어려운 기술, 기교 등을 보여 주었을 때 박수로서 힘을 주면 됩니다.
      관객의 박수는 공연자에게 큰 힘이 됩니다.
    </pre>
  </div>

  <div v-if="clap==4">
    <pre class="clap-detail">
      음악공연의 경우 기본적으로 1곡이 끝나면 박수를 칩니다.
      뮤지컬, 오페라는 감동적인 아리아나, 이중창, 합창이 끝났을 경우 박수를 쳐도 됩니다.
    </pre>
  </div>
</div>
</template>

<script>
export default {
  name : 'Clap',
  props: {
    clap: Number
  }
}
</script>

<style>
.clap-detail {
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
}
</style>