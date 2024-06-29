import { readUser } from '@/utils/cache';
import { User } from 'app'
import { defineStore } from 'pinia'

const useStore = defineStore('main', {
  state: () => {
    const user: Partial<User> = readUser();
    return { user }
  },
  actions: {
    updateUser(user: Partial<User>) {
      this.$patch({ user })
    }
  }
})


export default useStore;
