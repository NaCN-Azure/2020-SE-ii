<template>
    <a-card style="width:100%" :title="item.title">
        <a slot="extra" >
            more
        </a>
        <a-tabs default-active-key="1" @change="callback" v-if="modify">
            <a-tab-pane key="1" tab="Tab 1">
                Content of Tab Pane 1
            </a-tab-pane>
            <a-tab-pane key="2" tab="Tab 2" force-render>
                Content of Tab Pane 2
            </a-tab-pane>
            <a-tab-pane key="3" tab="Tab 3">
                Content of Tab Pane 3
            </a-tab-pane>
        </a-tabs>
    </a-card>
</template>

<script>
    const data = [
        {
            title: 'Title 1',
        },
        {
            title: 'Title 2',
        },
        {
            title: 'Title 3',
        },
        {
            title: 'Title 4',
        },
        {
            title: 'Title 5',
        },
        {
            title: 'Title 6',
        },
    ];
    export default {
        name: "couponCard",
        data(){
            return{
                data,
            }
        },
        methods:{
            callback(key) {
                console.log(key);
            },
        }
    }
</script>

<style scoped>

</style>