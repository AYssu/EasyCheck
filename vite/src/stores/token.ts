import {defineStore} from 'pinia'
import {ref} from 'vue'

export const user_token = defineStore(
    'easy_token',
    () => {
        const token = ref('')
        const set_token = (newToken: string) => {
            token.value = newToken
        }
        const remove_token = () => {
            token.value = ''
        }
        return {
            token,
            set_token,
            remove_token
        }
    },
    {
        persist: true
    }
)
