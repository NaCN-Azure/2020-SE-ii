<!--<template>-->
<!--    <a-list-->
<!--            class="comment-list"-->
<!--            :header="`${data.length} replies`"-->
<!--            item-layout="horizontal"-->
<!--            :data-source="data"-->
<!--    >-->

<!--        <a-list-item slot="renderItem" slot-scope="item">-->
<!--            <a-comment :userName="item.id" :avatar="item.avatar">-->

<!--                <p slot="content">-->
<!--                    {{ item.content }}-->
<!--                </p>-->

<!--            </a-comment>-->
<!--        </a-list-item>-->
<!--    </a-list>-->
<!--</template>-->
<!--<script>-->

<!--    import moment from 'moment';-->
<!--    import {mapActions, mapMutations} from "vuex";-->
<!--    const data = [];-->
<!--    export default {-->
<!--        name:"historyComment",-->
<!--        props:{-->
<!--            comments:{-->
<!--                type:Array-->
<!--            }-->
<!--        },-->
<!--        data() {-->

<!--            return {-->
<!--                data-->
<!--              }-->
<!--            },-->

<!--        mounted() {-->
<!--            this.getData();-->
<!--        },-->
<!--        methods: {-->
<!--            ...mapMutations([-->
<!--                'set_orderModalVisible',-->
<!--                'set_currentOrderRoom'-->
<!--            ]),-->
<!--            ...mapActions([-->

<!--            ]),-->

<!--            getData() {-->
<!--                console.log(this.comments)-->
<!--                 this.data=this.comments;-->
<!--            },-->
<!--        }-->
<!--    }-->
<!--</script>-->
<!--<script>-->
<!--    const data = [{-->

<!--        userName: `ant design vue part 1`,-->
<!--        avatar: 'https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png',-->
<!--        description:-->
<!--            'Ant Design, a design language for background applications, is refined by Ant UED Team.',-->
<!--        content:-->
<!--            'We supply a series of design principles, practical patterns and high quality design resources (Sketch and Axure), to help people create their product prototypes beautifully and efficiently.',-->
<!--    }];-->

<!--    console.log(data)-->

<!--    export default {-->
<!--        data() {-->
<!--            return {-->
<!--                data,-->
<!--                pagination: {-->
<!--                    onChange: page => {-->
<!--                        console.log(page);-->
<!--                    },-->
<!--                    pageSize: 3,-->
<!--                },-->
<!--                actions: [-->
<!--                    { type: 'star-o', text: '156' },-->
<!--                    { type: 'like-o', text: '156' },-->
<!--                    { type: 'message', text: '2' },-->
<!--                ],-->
<!--            };-->
<!--        },-->
<!--    };-->
<!--</script>-->


<template>
    <a-list
            class="comment-list"
            :header="`${data.length} replies`"
            item-layout="vertical"
            :data-source="data"
    >
        <a-list-item slot="renderItem" slot-scope="item">
            <a-list-item-meta :description="'信用值：'+item.credit">
                <a slot="title" >{{ item.userName}}</a>
                <a-avatar slot="avatar" shape="square"
                          size="large"
                          :style="{ backgroundColor: item.color, verticalAlign: 'middle' }"
                >{{item.userName[0]}}
                </a-avatar>
            </a-list-item-meta>
            <div>
                <a-rate style="font-size: 15px" :value="item.rate" :tooltips="desc" disabled />
                <span class="ant-rate-text">{{ desc[item.rate - 1] }}</span>
            </div>
            {{ item.content }}
        </a-list-item>
    </a-list>
</template>
<script>

    import moment from 'moment';
    import {mapActions, mapMutations} from "vuex";
    const data = [];
    export default {
        name:"historyComment",
        props:{
            comments:{
                type:Array
            }
        },
        data() {
            return {
                data,
                desc: ['terrible', 'bad', 'normal', 'good', 'wonderful'],
            }
        },
        mounted() {
            this.getData();
        },
        methods: {
            getData() {
                console.log(this.comments)
                this.data=this.comments;
            },
        }
    }
</script>
