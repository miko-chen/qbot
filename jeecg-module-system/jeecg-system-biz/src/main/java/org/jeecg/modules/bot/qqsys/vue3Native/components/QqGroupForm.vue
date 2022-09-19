<template>
  <a-spin :spinning="confirmLoading">
    <a-form ref="formRef" class="antd-modal-form" :labelCol="labelCol" :wrapperCol="wrapperCol">
      <a-row>
        <a-col :span="24">
          <a-form-item label="QQ群号" v-bind="validateInfos.groupId">
            <a-input v-model:value="formData.groupId" placeholder="请输入QQ群号" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="群名" v-bind="validateInfos.name">
            <a-input v-model:value="formData.name" placeholder="请输入群名" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="群公告" v-bind="validateInfos.announcement">
            <a-input v-model:value="formData.announcement" placeholder="请输入群公告" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="是否开启坦白说" v-bind="validateInfos.confessTalk">
	          <j-dict-select-tag v-model:value="formData.confessTalk" dictCode="isEnable" placeholder="请选择是否开启坦白说" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="是否允许群员邀请" v-bind="validateInfos.allowMemberInvite">
	          <j-dict-select-tag v-model:value="formData.allowMemberInvite" dictCode="isEnable" placeholder="请选择是否允许群员邀请" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="是否开启自动审批入群" v-bind="validateInfos.autoApprove">
	          <j-dict-select-tag v-model:value="formData.autoApprove" dictCode="isEnable" placeholder="请选择是否开启自动审批入群" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="是否允许匿名聊天" v-bind="validateInfos.anonymousChat">
	          <j-dict-select-tag v-model:value="formData.anonymousChat" dictCode="isEnable" placeholder="请选择是否允许匿名聊天" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="是否启用" v-bind="validateInfos.isEnable">
	          <a-input-number v-model:value="formData.isEnable" placeholder="请输入是否启用" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="到期时间" v-bind="validateInfos.expirationTime">
            <a-input v-model:value="formData.expirationTime" placeholder="请输入到期时间" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="余额" v-bind="validateInfos.balance">
            <a-input v-model:value="formData.balance" placeholder="请输入余额" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
      </a-row>
    </a-form>
  </a-spin>
</template>

<script lang="ts" setup>
  import { ref, reactive, defineExpose, nextTick, defineProps, computed } from 'vue';
  import { defHttp } from '/@/utils/http/axios';
  import { useMessage } from '/@/hooks/web/useMessage';
  import moment from 'moment';
  import JDictSelectTag from '/@/components/Form/src/jeecg/components/JDictSelectTag.vue';
  import { getValueType } from '/@/utils';
  import { saveOrUpdate } from '../QqGroup.api';
  import { Form } from 'ant-design-vue';
  
  const props = defineProps({
    disabled: { type: Boolean, default: false },
  });
  const formRef = ref();
  const useForm = Form.useForm;
  const emit = defineEmits(['register', 'ok']);
  const formData = reactive<Record<string, any>>({
    id: '',
    groupId: '',   
    id: '',
    name: '',   
    id: '',
    announcement: '',   
    id: '',
    confessTalk: undefined,
    id: '',
    allowMemberInvite: undefined,
    id: '',
    autoApprove: undefined,
    id: '',
    anonymousChat: undefined,
    id: '',
    isEnable: undefined,
    id: '',
    expirationTime: '',   
    id: '',
    balance: '',   
  });
  const { createMessage } = useMessage();
  const labelCol = ref<any>({ xs: { span: 24 }, sm: { span: 5 } });
  const wrapperCol = ref<any>({ xs: { span: 24 }, sm: { span: 16 } });
  const confirmLoading = ref<boolean>(false);
  //表单验证
  const validatorRules = {
  };
  const { resetFields, validate, validateInfos } = useForm(formData, validatorRules, { immediate: true });
  
  /**
   * 新增
   */
  function add() {
    edit({});
  }

  /**
   * 编辑
   */
  function edit(record) {
    nextTick(() => {
      resetFields();
      //赋值
      Object.assign(formData, record);
    });
  }

  /**
   * 提交数据
   */
  async function submitForm() {
    // 触发表单验证
    await validate();
    confirmLoading.value = true;
    const isUpdate = ref<boolean>(false);
    //时间格式化
    let model = formData;
    if (model.id) {
      isUpdate.value = true;
    }
    //循环数据
    for (let data in model) {
      //如果该数据是数组并且是字符串类型
      if (model[data] instanceof Array) {
        let valueType = getValueType(formRef.value.getProps, data);
        //如果是字符串类型的需要变成以逗号分割的字符串
        if (valueType === 'string') {
          model[data] = model[data].join(',');
        }
      }
    }
    await saveOrUpdate(model, isUpdate.value)
      .then((res) => {
        if (res.success) {
          createMessage.success(res.message);
          emit('ok');
        } else {
          createMessage.warning(res.message);
        }
      })
      .finally(() => {
        confirmLoading.value = false;
      });
  }


  defineExpose({
    add,
    edit,
    submitForm,
  });
</script>

<style lang="less" scoped>
  .antd-modal-form {
    height: 500px !important;
    overflow-y: auto;
    padding: 24px 24px 24px 24px;
  }
</style>
