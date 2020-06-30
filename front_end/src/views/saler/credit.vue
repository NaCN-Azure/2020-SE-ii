<template>
    <div>
    <a-table
            :columns="columns"
            :dataSource="data"
            bordered
    >
        <template
                v-for="col in ['phoneNumber','credit']"
                :slot="col"
                slot-scope="text, record"
        >
            <div :key="col">
                <a-input
                        v-if="record.editable"
                        style="margin: -5px 0"
                        :value="text"
                        @change="e => handleChange(e.target.value, record.key, col)"
                />
                <template v-else>
                    {{ text }}
                </template>
            </div>
        </template>
        <template slot="operation" slot-scope="text,record">
            <div class="editable-row-operations">
            <span v-if="record.editable">
              <a @click="() => save(record.key)">Save</a>
              <a-popconfirm title="Sure to cancel?" @confirm="() => cancel(record.key)">
                <a>Cancel</a>
              </a-popconfirm>
            </span>
           <span v-else>
               <a :disabled="editingKey !== ''" @click="() => edit(record)">Edit</a>
            </span>

            </div>
        </template>

    </a-table>
    </div>


<!--    <template>-->
<!--        <a-timeline>-->
<!--            <a-timeline-item>Create a services site 2015-09-01</a-timeline-item>-->
<!--            <a-timeline-item>Solve initial network problems 2015-09-01</a-timeline-item>-->
<!--            <a-timeline-item color="red">-->
<!--                <a-icon slot="dot" type="clock-circle-o" style="font-size: 16px;" />-->
<!--                Technical testing 2015-09-01-->
<!--            </a-timeline-item>-->
<!--            <a-timeline-item>Network problems being solved 2015-09-01</a-timeline-item>-->
<!--        </a-timeline>-->
<!--    </template>-->
</template>


<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    const moment = require('moment')

    const data=[]
    const columns=[
        {
            title: '用户邮箱',
            dataIndex: 'email',
        },
        {
            title: '用户名',
            dataIndex: 'userName',
            scopedSlots: { customRender: 'userName' },
        },

        {
            title: '用户手机号',
            dataIndex: 'phoneNumber',
            scopedSlots: { customRender: 'phoneNumber' },
        },
        {
            title: '信用值',
            dataIndex: 'credit',
            scopedSlots: { customRender: 'credit' },
        },
        {
            title: 'operation',
            dataIndex: 'operation',
            scopedSlots: { customRender: 'operation' },
        },
    ]
    export default {
        name: "credit",
        data(){
            this.cacheData = data.map(item => ({ ...item }));
            return{
                data,
                formLayout: 'horizontal',
                pagination: {},
                columns,
                editingKey: '',
            }
        },
        components: {
        },
        computed:{
            ...mapGetters([
                'clientList',
            ]),
        },
        async mounted(){
            await this.getClientList()
            for (let i = 0; i < this.clientList.length && data.length< this.clientList.length; i++) {
                data.push({
                    key:i.toString(),
                    id:this.clientList[i].id,
                    email:this.clientList[i].email,
                    userName:this.clientList[i].userName,
                    phoneNumber:this.clientList[i].phoneNumber,
                   credit:this.clientList[i].credit
                });
                //深拷贝两个数组
                this.cacheData.push({
                    key:i.toString(),
                    id:this.clientList[i].id,
                    mail:this.clientList[i].email,
                    userName:this.clientList[i].userName,
                    phoneNumber:this.clientList[i].phoneNumber,
                    credit:this.clientList[i].credit
                })
            }
        },
        methods:{
            ...mapActions([
                'getClientList',
                'addCreditById'
            ]),
            ...mapMutations([

            ]),
            addCredit(record){
                this.addCreditById({
                    id:record.id,
                    credit:record.credit
                })
            },
            edit(keys) {
                //解引用是浅拷贝
                const key=keys.key
                const newData = [...this.data];
                const target = newData.filter(item => key === item.key)[0];
                console.log(keys)
                this.editingKey = key;
                if (target) {
                    target.editable = true;
                    this.data = newData;
                }
            },
            handleChange(value, key, column) {
                const newData = [...this.data];
                //map深，filter是深拷贝（独立，但filter仅仅针对第一层
                const target = newData.filter(item => key === item.key)[0];

                if (target) {

                    target[column] = value;

                    this.data = newData;
                }
            },
            save(key) {
                const newData = [...this.data];
                const newCacheData = [...this.cacheData];
                const target = newData.filter(item => key === item.key)[0];
                const targetCache = newCacheData.filter(item => key === item.key)[0];
                console.log("outside")
                if (target && targetCache) {
                    console.log("Inside",target)
                    this.addCredit(target)
                    delete target.editable;
                    this.data = newData;
                    Object.assign(targetCache, target);
                    this.cacheData = newCacheData;
                }
                this.editingKey = '';
            },
            cancel(key) {
           
                const newData = [...this.data];
                const target = newData.filter(item => key === item.key)[0];
                this.editingKey = '';
                if (target) {

                    console.log(data)
                    console.log(this.cacheData)
                    //好像是浅拷贝（第一层深）
                    Object.assign(target, this.cacheData.filter(item => key === item.key)[0]);
                    delete target.editable;
                    this.data = newData;
                    console.log(data)
                    console.log(this.cacheData)
                }
            },
        }
    }
</script>

<style scoped lang="less">
    .manageHotel-wrapper {
        padding: 50px;
        .chart {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-top: 20px
        }
    }
</style>
<style lang="less">
    .manageHotel-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">

</style>