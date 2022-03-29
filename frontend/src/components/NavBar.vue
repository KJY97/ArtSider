<template>
  <v-card 
  class="overflow-hidden pt-2"
  style="background-color: #FEFEF4"
  flat>
    <v-app-bar 
    flat
    inverted-scroll
    color="transparent"
    >
    <v-toolbar-title class="text-uppercase">
      <span class="font-weight-light"> ArtSider </span>
    </v-toolbar-title>
    
    <v-spacer></v-spacer>
    <v-menu
      bottom
    >
      <template v-slot:activator="{on, attrs}">
        <v-btn
          text  
          v-bind="attrs"
          v-on="on"
        >
          공연 카테고리
        <v-icon
        right
        >
          mdi-arrow-down-drop-circle-outline
        </v-icon>
        </v-btn>
      </template>

      <v-list>
        <v-list-item
          v-for="(category, i) in categories"
          :key="i"
        >
          <v-btn text>
            <v-list-item-title> {{ category.title }} </v-list-item-title>
          </v-btn>
        </v-list-item>
      </v-list>

    </v-menu>

      <v-spacer></v-spacer>

    <v-text-field
      solo
      flat
      outlined
      rounded
      background-color="#FFF0E1"
      color="black"
    >
      <v-btn
        slot="append"
        icon
      >
        <v-icon
          color="gray"
        >
          mdi-magnify
        </v-icon>
      </v-btn>

    </v-text-field>

      <v-spacer></v-spacer>

      <v-btn text color="gray">
        <span> 공연 에티켓 </span>
      </v-btn>

      <v-btn text color="gray">
        <span>로그아웃</span>
      </v-btn>

        <v-btn text color="gray">
        <span>마이페이지</span>
      </v-btn>

    </v-app-bar>
  </v-card>
</template>

<script>
export default {
  name: 'NavBar',
  data(){
    return{
      categories: [
        { title: "뮤지컬"},
        { title: "연극"},
        { title: "콘서트"},
        { title: "클래식/오페라/무용"},
        { title: "아동/키즈"}
      ]
    }
  }
}
</script>

<style>
.v-text-field.v-text-field--enclosed .v-text-field__details{
  display: none;
}

</style>