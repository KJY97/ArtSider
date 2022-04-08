<script>
//Importing Bar class from the vue-chartjs wrapper
import {Bar} from 'vue-chartjs'
//Exporting this so it can be used in other components
export default {
  extends: Bar,
  props:{
    scoreList: Array,
  },

  data() {
    return {
      datacollection: {
        //Data to be represented on x-axis
        labels: ['1점', '2점', '3점', '4점', '5점', '6점', '7점', '8점', '9점', '10점'],
        datasets: [
          {
            label: '별점 개수',
            backgroundColor: '#f87979',
            pointBackgroundColor: 'white',
            borderWidth: 1,
            pointBorderColor: '#249EBF',
            //Data to be represented on y-axis
            // data: [4, 2, 3, 5, 3, 1, 2, 4, 5, 7]
            data: this.scoreList
          }
        ]
      },
      //Chart.js options that controls the appearance of the chart
      options: {
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero: true,
            },
            gridLines: {
              display: false
            },
          }],
          xAxes: [ {
            ticks: {
              minRotation: 40
            },

            gridLines: {
              display: false
            }
          }]
        },
        legend: {
            display: true
          },
        responsive: true,
        maintainAspectRatio: false
      }
    }
  },

  mounted() {
    // 그래프 소수점 제거
    if(Math.max(...this.scoreList) < 10 ){
      this.options.scales.yAxes[0].ticks.stepSize = 1
    }

    //renderChart function renders the chart with the datacollection and options object.
    this.renderChart(this.datacollection, this.options)
  }
}
</script>