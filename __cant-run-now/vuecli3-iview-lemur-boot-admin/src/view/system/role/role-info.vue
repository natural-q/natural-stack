<template>
  <Modal v-model="isShow" width="65%" :closable="false" :mask-closable="false">
    <div slot="header" class="info-header">
      <div class="ivu-modal-header-inner">角色表</div>
      <a class="ivu-modal-close" @click="closeModel"><i class="ivu-icon ivu-icon-ios-close"></i></a>
    </div>
    <div>
      <Form ref="roleForm" :model="form" :label-width="80" :rules="ruleValidate">
        <Row>
          <Col span="8">
            <FormItem label="角色名称" prop="name">
              <Input v-model="form.name" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="备注" prop="tips">
              <Input v-model="form.tips" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="微信ID">
              <Input v-model="form.wxid" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="钉钉ID">
              <Input v-model="form.ddid" :disabled="disable"></Input>
            </FormItem>
          </Col>
        </Row>
      </Form>
    </div>
    <div slot="footer">
      <Button @click="closeModel">取消</Button>
      <Button type="primary" @click="submit"  v-show="!disable">提交</Button>
    </div>
  </Modal>
</template>

<script>

import { C, U } from '@/libs/api.request'

export default {
  name: 'role-info',
  data () {
    return {
      form: {},
      isShow: false,
      type: 'add',
      disable: false,
      ruleValidate: {
        name: [
          { required: true, message: '角色名称不允许为空', trigger: 'blur' }
        ],
        tips: [
          { required: true, message: '备注不允许为空', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    openModel (type, data) {
      this.isShow = true
      this.type = type
      if (data) {
        this.form = data
      } else {
        this.form = {}
      }
      this.disable = type === 'detail'
    },
    closeModel () {
      this.isShow = false
    },
    submit () {
      this.$refs.roleForm.validate((valid) => {
        if (valid) {
          switch (this.type) {
            case 'create':
              C('role', this.form).then(data => {
                this.isShow = false
                this.$emit('handleSearch')
              })
              break
            case 'update':
              U('role', this.form).then(data => {
                this.isShow = false
                this.$emit('handleSearch')
              })
              break
          }
        } else {
          this.$Message.error('请检查填写的数据')
        }
      })
    }
  },
  mounted: function () {
  }
}
</script>

<style scoped>
  .info-header {
    height: 40px;
    color: #31708f;
    background-color: #d9edf7;
    border-color: #bce8f1;
  }

  .ivu-modal-header-inner {
    margin: 10px 15px 0px 0px;
    padding-left: 15px;
    height: 40px;
  }

  .ivu-modal-close {
    margin: 10px 15px 0px 0px;
  }

  .ivu-form-item {
    width: 260px;
  }
</style>
