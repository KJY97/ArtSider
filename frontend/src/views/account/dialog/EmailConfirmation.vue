<template>
  <v-dialog
  v-model="confirmDialog"
  dark
  max-width="500px"
  @click:outside="() => {$emit('close-dialog') }"
  >
    <v-card>
      <v-container>
        <v-form  
          v-if="confirmDialog"
          class="confirm-form">
            <v-text-field
              v-model="confirmationNumber"
              label="이메일 인증 번호 입력"
              required
            >
            <span
              slot="append"
            >
              {{ minutes }} : {{ seconds }}
            </span>
            </v-text-field>

          <v-btn 
            outlined
            @click="signUp"
            >
              회원가입
          </v-btn>
        </v-form>
      </v-container>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  name: 'EmailConfirmation',
  props:{
    userData : Object,
    confirmDialog : Boolean
  },

  data(){
    return{
      confirmationNumber : '',
      timerCount: 180,
      minutes: 3,
      seconds: 30,
      timer: null
    }
  },

  methods:{
    startTimer(){
      if(this.timerCount > 0){
        this.timer = setInterval(() => {
              this.timerCount -= 1
          }, 1000)
        } 
    },

    signUp(){
      this.confirmationNumber = ''
      this.$emit('close-dialog')
    }
  },

  watch: {
    confirmDialog: function(){
      if(this.confirmDialog){
        this.timerCount = 180
        this.minutes = 3
        this.seconds = 30
        this.startTimer()
      } else{
        clearInterval(this.timer)
      }
    },

    timerCount: function(){
      if(this.seconds > 0){
        this.seconds -= 1
      } else if(this.minutes > 0) {
        this.minutes -= 1
        this.seconds = 59
      } else{
        this.$emit('close-dialog')
      }

    }
  },

}
</script>

<style>


</style>