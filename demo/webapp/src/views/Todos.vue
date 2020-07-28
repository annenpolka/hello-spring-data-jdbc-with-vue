<template>
  <el-row>
    <el-col>
      <el-card>
        <el-table :data="todos" style="width: 100%;">
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
          <el-table-column prop="finished" label="finished" width="300">
            <template slot-scope="scope">
              <span v-if="scope.row.finished">☑</span>
            </template>
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
      todos: []
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
    }
  }
};
</script>

<style scoped></style>
