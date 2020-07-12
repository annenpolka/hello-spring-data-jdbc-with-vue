<template>
  <div></div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'Todo',
  data() {
    return {
      todos: [],
    };
  },
  created: async function() {
    await this.refresh();
  },
  methods: {
    refresh: async function() {
      const res = await axios.get('http://localhost:8080/');
      this.todos = res.data.todos;
      console.info(this.todos);
    },
  },
};
</script>

<style scoped></style>
