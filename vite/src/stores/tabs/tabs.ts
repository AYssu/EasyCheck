import { defineStore } from 'pinia';
import {ref} from "vue";
export const tabs_status = defineStore(
    'easy_tabs_status',
    () => {
        const tab_list = ref<Tab[]>([]); // 初始状态为 true
        const set_tab_list = (new_tab_list: Tab) => {
            if(tab_list.value.some(item => item.path === new_tab_list.path))
            {
                // console.log('tab已经存在')
                return
            }
            // console.log('tab不存在,创建')
            tab_list.value.push(new_tab_list)
        }

        const remove_tab_list = () => {

        }
        const get_tab_list = () => {
            return tab_list.value
        }
        return {
            tab_list,
            set_tab_list,
            remove_tab_list,
            get_tab_list
        }

    },{
        persist:  {
            storage: sessionStorage,
        },
    }
)

export type Tab = {
    title: string;
    path: string;
};
