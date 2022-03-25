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
      posters: ['poster1', 'poster2', 'poster3', 'poster4', 'poster5', 'poster6']
    }
  },

  mounted(){
    if(this.posters){
      const circle = document.querySelector('#circle')
      let yDeg = 0
      this.posters.forEach((poster) => {
        const article = document.createElement('article')
        article.innerText = poster
        circle.appendChild(article)
      })

      const articles = circle.childNodes

      articles.forEach((article)=>{
        const articleWidth = article.clientWidth
        const zDistance = parseInt(((articleWidth * 6) / 3.14) / 2) + 130
        console.log('zDistance', zDistance)

        article.style.transform = `rotateY(${yDeg}deg) translateZ(-${zDistance}px)`
        yDeg += 60
      })
    }
  }

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
  height: 90%;
  perspective: 1300px;
  overflow: hidden;
}
.carousel-wrapper #circle{
  position: absolute;  
  width: 40%;
  height: 80%;
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