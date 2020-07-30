<template>
  <el-row>
    <el-col :span="24">
      <el-card class="box-card">
        <el-col :span="8">
          <el-input
            v-model="request.todoTitle"
            placeholder="New Todo"
            clearable
          >
          </el-input>
        </el-col>
        <el-col :span="8">
          <el-button type="success" @click="addTodo">追加</el-button>
        </el-col>
      </el-card>
    </el-col>
    <el-col>
      <el-card>
        <el-table
          :data="todos"
          :default-sort="{ prop: 'todoId', order: 'descending' }"
          style="width: 100%;"
        >
          <el-table-column
            prop="todoId"
            label="id"
            width="300"
          ></el-table-column>
          <el-table-column
            prop="todoTitle"
            label="title"
            width="300"
          ></el-table-column>
          <el-table-column
            prop="createdAt"
            label="createdAt"
            width="300"
          ></el-table-column>
          <el-table-column
            prop="finished"
            label="finished"
            width="300"
            v-slot="todo"
          >
            <!-- v-slotでその列で使ってるオブジェクトの値を取ってこれる -->
            <el-checkbox
              v-model="todo.row.finished"
              @change="updateTodo(todo.row)"
            ></el-checkbox>
          </el-table-column>
          <el-table-column
            prop="operation"
            label="Ops"
            width="200"
            align="left"
            v-slot="todo"
          >
            <el-button
              size="mini"
              type="danger"
              @click="deleteTodo(todo.row.todoId)"
              >×</el-button
            >
          </el-table-column>
        </el-table>
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
import axios from "axios";

export default {
  name: "Todo",
  data() {
    return {
      todos: [],
      request: {
        todoTitle: undefined
      }
    };
  },
  created: async function() {
    await this.refresh();
  },
  methods: {
    refresh: async function() {
      const res = await axios.get("http://localhost:8080/");
      this.todos = res.data.todos;
      console.info(this.todos);
    },
    addTodo: async function() {
      await axios.post("http://localhost:8080/", this.request);
      await this.refresh();
    },
    deleteTodo: async function(todoId) {
      await axios.delete("http://localhost:8080/" + todoId);
      await this.refresh();
    },
    updateTodo: async function(todo) {
      console.log(todo);
      await axios.post("http://localhost:8080/" + todo.todoId, todo);
      await this.refresh();
    }
  }
};
</script>

<style scoped></style>
