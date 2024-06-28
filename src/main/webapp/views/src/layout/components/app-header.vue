<script lang="ts" setup>
import { ArrowDown, UserFilled } from '@element-plus/icons-vue'
import { ElDropdown, ElDropdownItem, ElDropdownMenu, ElIcon, ElMessage, ElMessageBox } from 'element-plus'
import { useRouter } from 'vue-router';

const router = useRouter();

const onCommand = (command: "profile" | "logout") => {
  switch (command) {
    case "profile":
      router.push("/profile")
      break;
    case "logout":
      ElMessageBox.confirm("确定退出登录吗？", "提示", {
        type: 'warning'
      }).then(() => {
        router.push("/login")
      }).catch(() => {
        ElMessage.info('取消注销~');
      })
      break;
    default:
      break;
  }
}

</script>

<template>
  <div class="logo flex-center" style="width: 200px;">管理后台</div>
  <div class="avatar" style="padding-right: 16px;">
    <el-dropdown @command="onCommand">
      <span class="el-dropdown-link flex-center pointer">
        <el-icon><UserFilled /></el-icon>
        <span>Dropdown</span>
        <el-icon class="el-icon--right">
          <arrow-down />
        </el-icon>
      </span>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item command="profile">个人中心</el-dropdown-item>
          <el-dropdown-item command="logout" divided>注销</el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
  </div>
</template>
