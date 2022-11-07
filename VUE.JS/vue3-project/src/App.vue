<template>
  <div class="name">
    {{ name }}
  </div>
  <div>
    {{ greeting() }}
  </div>
  <div>
    {{ greeting2('Bohyeon') }}
  </div>
  <div>
    {{ greeting2(name) }}
  </div>
  <div>
    {{ greeting3 }}
  </div>
</template>

<script>
export default {
  setup() {
    const name = "Kossie Coder";

    // 함수사용
    const greeting = () => {
      return "Hello"
    }

    // 함수사용2
    const greeting2 = (name) => {
      return "Hello " + name
    }

    const greeting3 = greeting2(name);

    return {
      name,
      greeting,
      greeting2,
      greeting3,
    };
  },
};
</script>

<style>
.name {
  color: red;
}
</style>
