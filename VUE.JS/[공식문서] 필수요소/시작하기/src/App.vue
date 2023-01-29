<template>

  <div class="menu">
    <a v-for="i in menues" :key="i">{{ i }}</a>
  </div>

  <div v-for="(d,i) in products" :key="i">
    <h4 class="red" :style="스타일">{{ d }} 원룸</h4>
    <p>{{ price1 }} 만원</p>
    <button @click="increase(i)">허위매물 신고</button> <span>신고수 : {{ 신고수[i] }}</span>
  </div>

  <div id="counter">
  Counter: {{ counter }}
</div>

<div id="bind-attribute">
  <span v-bind:title="message">
    여기에 마우스를 올려두고 잠시 기다리면 제목이 동적으로 바뀝니다!
  </span>
</div>
</template>

<script>


export default {
  name: 'App',
  data() {
    return {
      신고수 : [0,0,0],
      products: ['역삼동원룸', '천호동원룸', '마포구원룸'],
      menues: ["Home", 'Shop', 'About'],
      price1: 50,
      counter: 0,
      message: '이 페이지를 다음 시간에 열었습니다. ' + new Date().toLocaleString(),
      
    }
  },

  mounted() {
    setInterval(() => {
      this.counter++
    }, 1000)
  },

  methods: {
    increase(i) {
      this.신고수[i]++
    },
  
  },

  components: {
    
  }
}
</script>



<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

.menu{
  background: darkslateblue;
  padding : 15px;
  border-radius: 5px;
}

.menu a {
  color: white;
  padding: 10px;
}
</style>
