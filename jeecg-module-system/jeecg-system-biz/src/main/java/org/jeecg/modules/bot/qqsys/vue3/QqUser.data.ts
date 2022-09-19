import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: 'QQ号',
    align:"center",
    dataIndex: 'qq'
   },
   {
    title: '昵称',
    align:"center",
    dataIndex: 'nickName'
   },
   {
    title: '邮箱',
    align:"center",
    dataIndex: 'email'
   },
   {
    title: '年龄',
    align:"center",
    dataIndex: 'age'
   },
   {
    title: '等级',
    align:"center",
    dataIndex: 'level'
   },
   {
    title: '登录方式',
    align:"center",
    dataIndex: 'sign'
   },
   {
    title: '性别',
    align:"center",
    dataIndex: 'sex_dictText'
   },
   {
    title: '是否为管理员',
    align:"center",
    dataIndex: 'isAdmin_dictText'
   },
   {
    title: '是否启用',
    align:"center",
    dataIndex: 'isEnable_dictText'
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
    label: 'QQ号',
    field: 'qq',
    component: 'InputNumber',
  },
  {
    label: '昵称',
    field: 'nickName',
    component: 'Input',
  },
  {
    label: '邮箱',
    field: 'email',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: false},
                 { pattern: /^([\w]+\.*)([\w]+)@[\w]+\.\w{3}(\.\w{2}|)$/, message: '请输入正确的电子邮件!'},
          ];
     },
  },
  {
    label: '年龄',
    field: 'age',
    component: 'InputNumber',
  },
  {
    label: '等级',
    field: 'level',
    component: 'InputNumber',
  },
  {
    label: '登录方式',
    field: 'sign',
    component: 'Input',
  },
  {
    label: '性别',
    field: 'sex',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"sex"
     },
  },
  {
    label: '是否为管理员',
    field: 'isAdmin',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"isAdmin"
     },
  },
  {
    label: '是否启用',
    field: 'isEnable',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"isEnable"
     },
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
