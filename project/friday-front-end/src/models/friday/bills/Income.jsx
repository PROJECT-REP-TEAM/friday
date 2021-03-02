import { selectAll , editIncomeBykey} from "@/services/friday/bills/Income";

export default {
  namespace: "incomeMSG",

  state: {
    data: []
  },

  effects: {
    /**
     * @param payload 参数
     * @param call 执行异步函数调用接口
     * @param put 发出一个 Action，类似于 dispatch 将服务端返回的数据传递给上面的state
     * @returns {IterableIterator<*>}
     */
      *selectAll({ payload }, { call, put }) {
      const response = yield call(selectAll, payload);
      yield put({
        // 这行对应下面的reducers处理函数名字
        type: "queryIncome",
        payload: response
      });
    },

      *editIncome({ payload }, { call, put }) {
        const response = yield call(editIncomeBykey, payload);
        yield put({
          type: "editIncome",
          payload: response
        });
      },
  },

  reducers: {
    /**
     *
     * @param state
     * @param action
     * @returns {{[p: string]: *}}
     */
    queryIncome(state, action) {
      return {
        ...state,
        data: action.payload
      };
    },

    editIncome(state, action) {
      return {
        ...state,
        data: action.payload
      };
    },
  }
};
