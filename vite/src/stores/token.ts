import { defineStore } from 'pinia'
import { ref } from 'vue'

export const user_token = defineStore(
  'easy_token',
  () => {
    const token: any = ref({})
    const set_token = (new_token: any) => {
      token.value = new_token
    }
    const remove_token = () => {
      token.value = {}
    }
    return {
      token,
      set_token,
      remove_token,
    }
  },
  {
    persist: true,
  },
)
