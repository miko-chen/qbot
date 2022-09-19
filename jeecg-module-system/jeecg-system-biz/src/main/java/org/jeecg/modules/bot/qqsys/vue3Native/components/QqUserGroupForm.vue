<template>
  <a-spin :spinning="confirmLoading">
    <a-form ref="formRef" class="antd-modal-form" :labelCol="labelCol" :wrapperCol="wrapperCol">
      <a-row>
        <a-col :span="24">
          <a-form-item label="QQ" v-bind="validateInfos.qq">
            <a-input v-model:value="formData.qq" placeholder="请输入QQ" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="群号" v-bind="validateInfos.groupId">
            <a-input v-model:value="formData.groupId" placeholder="请输入群号" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="入群时间" v-bind="validateInfos.joinTimestamp">
            <a-input v-model:value="formData.joinTimestamp" placeholder="请输入入群时间" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="上次发言时间" v-bind="validateInfos.lastSpeakTimestamp">
            <a-input v-model:value="formData.lastSpeakTimestamp" placeholder="请输入上次发言时间" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="马甲" v-bind="validateInfos.memberName">
            <a-input v-model:value="formData.memberName" placeholder="请输入马甲" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="权限" v-bind="validateInfos.permission">
            <a-input v-model:value="formData.permission" placeholder="请输入权限" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="剩余禁言时间" v-bind="validateInfos.mutetimeremaining">
            <a-input v-model:value="formData.mutetimeremaining" placeholder="请输入剩余禁言时间" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="特殊称号" v-bind="validateInfos.specialTitle">
            <a-input v-model:value="formData.specialTitle" placeholder="请输入特殊称号" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="上次签到时间" v-bind="validateInfos.lastSignTime">
            <a-input v-model:value="formData.lastSignTime" placeholder="请输入上次签到时间" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="积分" v-bind="validateInfos.integral">
            <a-input v-model:value="formData.integral" placeholder="请输入积分" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="是否启用" v-bind="validateInfos.isEnable">
	          <a-input-number v-model:value="formData.isEnable" placeholder="请输入是否启用" style="width: 100%" :disabled="disabled"/>
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
  import { getValueType } from '/@/utils';
  import { saveOrUpdate } from '../QqUserGroup.api';
  import { Form } from 'ant-design-vue';
  
  const props = defineProps({
    disabled: { type: Boolean, default: false },
  });
  const formRef = ref();
  const useForm = Form.useForm;
  const emit = defineEmits(['register', 'ok']);
  const formData = reactive<Record<string, any>>({
    id: '',
    qq: '',   
    id: '',
    groupId: '',   
    id: '',
    joinTimestamp: '',   
    id: '',
    lastSpeakTimestamp: '',   
    id: '',
    memberName: '',   
    id: '',
    permission: '',   
    id: '',
    mutetimeremaining: '',   
    id: '',
    specialTitle: '',   
    id: '',
    lastSignTime: '',   
    id: '',
    integral: '',   
    id: '',
    isEnable: undefined,
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
