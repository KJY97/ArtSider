<template>
  <main class="carousel-wrapper">
    <section id="circle"> 
    </section>
  </main>
</template>

<script>
export default {
  name: 'HomeCarousel',
  data(){
    return{
      posters: [],
      popularList: [],
    }
  },
  created: function () {
    this.$store.dispatch('getPopularShow')
    .then((res) => {
      this.popularList = res.data.items.slice(0,6)
      for (let show of this.popularList) {
      this.posters.push(show.posterPath)
      }
    })
  },
  watch: {
    posters: function () {
      if(this.posters){
      const circle = document.querySelector('#circle')
      window.addEventListener('resize', () => {
        const articles = circle.childNodes
        articles.forEach((article)=>{
        const articleWidth = article.clientWidth
        const zDistance = parseInt(((articleWidth * 6) / 3.141592) / 2) * 1.5

        article.style.transform = `rotateY(${yDeg}deg) translateZ(-${zDistance}px)`
        yDeg += 60
        })
      })
      let yDeg = 0
      this.posters.forEach((poster) => {
        const article = document.createElement('article')
        var img = document.createElement("IMG");
        img.setAttribute("src", poster);
        img.style = "width: 100%; height: 100%;"
        article.appendChild(img)
        circle.appendChild(article)
      })

      const articles = circle.childNodes

      articles.forEach((article)=>{
        const articleWidth = article.clientWidth
        const zDistance = parseInt(((articleWidth * 6) / 3.141592) / 2) * 1.5
        console.log('zDistance', zDistance)

        article.style.transform = `rotateY(${yDeg}deg) translateZ(-${zDistance}px)`
        yDeg += 60
      })
      }
    },
  },
}
</script>

<style>
@keyframes ani {
  0% { transform: rotateY(0deg);}
  100% {transform: rotateY(360deg);}
}

.carousel-wrapper{
  position: relative;
  width: 100%;
  height: 70vh;
  perspective: 1300px;
  overflow: hidden;
  background-color: black;
}
.carousel-wrapper #circle{
  position: absolute;  
  width: 40%;
  height: 90%;
  top: 10%;
  left: 30%;
  transform-style: preserve-3d;
  animation: ani linear 30s infinite;
}

.carousel-wrapper #circle article{
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0px;
  left: 0px;
  background-color: gray;
  backface-visibility: hidden;
}


</style>