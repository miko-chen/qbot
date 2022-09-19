import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: 'QQ',
    align:"center",
    dataIndex: 'qq'
   },
   {
    title: '群号',
    align:"center",
    dataIndex: 'groupId'
   },
   {
    title: '入群时间',
    align:"center",
    dataIndex: 'joinTimestamp'
   },
   {
    title: '上次发言时间',
    align:"center",
    dataIndex: 'lastSpeakTimestamp'
   },
   {
    title: '马甲',
    align:"center",
    dataIndex: 'memberName'
   },
   {
    title: '权限',
    align:"center",
    dataIndex: 'permission'
   },
   {
    title: '剩余禁言时间',
    align:"center",
    dataIndex: 'mutetimeremaining'
   },
   {
    title: '特殊称号',
    align:"center",
    dataIndex: 'specialTitle'
   },
   {
    title: '上次签到时间',
    align:"center",
    dataIndex: 'lastSignTime'
   },
   {
    title: '积分',
    align:"center",
    dataIndex: 'integral'
   },
   {
    title: '是否启用',
    align:"center",
    dataIndex: 'isEnable'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: 'QQ',
    field: 'qq',
    component: 'Input',
  },
  {
    label: '群号',
    field: 'groupId',
    component: 'Input',
  },
  {
    label: '入群时间',
    field: 'joinTimestamp',
    component: 'Input',
  },
  {
    label: '上次发言时间',
    field: 'lastSpeakTimestamp',
    component: 'Input',
  },
  {
    label: '马甲',
    field: 'memberName',
    component: 'Input',
  },
  {
    label: '权限',
    field: 'permission',
    component: 'Input',
  },
  {
    label: '剩余禁言时间',
    field: 'mutetimeremaining',
    component: 'Input',
  },
  {
    label: '特殊称号',
    field: 'specialTitle',
    component: 'Input',
  },
  {
    label: '上次签到时间',
    field: 'lastSignTime',
    component: 'Input',
  },
  {
    label: '积分',
    field: 'integral',
    component: 'Input',
  },
  {
    label: '是否启用',
    field: 'isEnable',
    component: 'InputNumber',
  },
	// TODO 主键隐藏字段，目前写死为ID
	{
	  label: '',
	  field: 'id',
	  component: 'Input',
	  show: false
	},
];
