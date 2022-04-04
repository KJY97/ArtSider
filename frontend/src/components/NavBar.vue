<template>
  <v-app-bar
  app
  scroll-target="#main"
  flat
  inverted-scroll
  style="background-color: rgba(233,30,99,0.9)"
  >
  <v-toolbar-title class="text-uppercase">
    <span class="font-weight-light"> ArtSider </span>
  </v-toolbar-title>
  
  <div class="menu-wrapper" style="margin-left: 50px;">
    <v-menu
      bottom
    >
      <template v-slot:activator="{on, attrs}">
        <v-btn
          width="218px"
          text  
          v-bind="attrs"
          v-on="on"
        >
        <span style="color: rgb(246,247,235);"> 공연 카테고리 </span>
        <v-icon
        right
        style="color: rgb(246,247,235)"
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
          <v-btn text style="width:100%;">
            <v-list-item-title> {{ category.title }} </v-list-item-title>
          </v-btn>
        </v-list-item>
      </v-list>

    </v-menu>
  </div>

    <v-spacer></v-spacer>

  <v-text-field
    solo
    flat
    dense
    rounded
    background-color="#FFFFFF"
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

    <v-btn text color="black">
      <span  style="color: rgb(246,247,235);"> 공연 에티켓 </span>
    </v-btn>

    <v-btn text color="gray">
      <span  style="color: rgb(246,247,235);">로그아웃</span>
    </v-btn>

      <v-btn text color="gray">
      <span  style="color: rgb(246,247,235);">마이페이지</span>
    </v-btn>

  </v-app-bar>
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
      ],
    }
  },



}
</script>

<style>

.v-text-field.v-text-field--enclosed .v-text-field__details{
  display: none;
}

</style>