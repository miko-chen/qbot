import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: 'QQ群号',
    align:"center",
    dataIndex: 'groupId'
   },
   {
    title: '群名',
    align:"center",
    dataIndex: 'name'
   },
   {
    title: '群公告',
    align:"center",
    dataIndex: 'announcement'
   },
   {
    title: '是否开启坦白说',
    align:"center",
    dataIndex: 'confessTalk_dictText'
   },
   {
    title: '是否允许群员邀请',
    align:"center",
    dataIndex: 'allowMemberInvite_dictText'
   },
   {
    title: '是否开启自动审批入群',
    align:"center",
    dataIndex: 'autoApprove_dictText'
   },
   {
    title: '是否允许匿名聊天',
    align:"center",
    dataIndex: 'anonymousChat_dictText'
   },
   {
    title: '是否启用',
    align:"center",
    dataIndex: 'isEnable'
   },
   {
    title: '到期时间',
    align:"center",
    dataIndex: 'expirationTime'
   },
   {
    title: '余额',
    align:"center",
    dataIndex: 'balance'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: 'QQ群号',
    field: 'groupId',
    component: 'Input',
  },
  {
    label: '群名',
    field: 'name',
    component: 'Input',
  },
  {
    label: '群公告',
    field: 'announcement',
    component: 'Input',
  },
  {
    label: '是否开启坦白说',
    field: 'confessTalk',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"isEnable"
     },
  },
  {
    label: '是否允许群员邀请',
    field: 'allowMemberInvite',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"isEnable"
     },
  },
  {
    label: '是否开启自动审批入群',
    field: 'autoApprove',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"isEnable"
     },
  },
  {
    label: '是否允许匿名聊天',
    field: 'anonymousChat',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"isEnable"
     },
  },
  {
    label: '是否启用',
    field: 'isEnable',
    component: 'InputNumber',
  },
  {
    label: '到期时间',
    field: 'expirationTime',
    component: 'Input',
  },
  {
    label: '余额',
    field: 'balance',
    component: 'Input',
  },
	// TODO 主键隐藏字段，目前写死为ID
	{
	  label: '',
	  field: 'id',
	  component: 'Input',
	  show: false
	},
];
