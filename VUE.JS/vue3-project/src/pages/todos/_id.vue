<template>
    <div>Tpdo Page</div>
</template>

<script>
import { useRouter  } from 'vue-router'

export default {
    setup() {
        const route = useRouter();

        console.log(route.params.id);
    }
}
</script>

<style>

</style>