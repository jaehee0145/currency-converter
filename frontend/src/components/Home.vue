<template>
  <div class="container">
    <div class="row mt-5 mb-5">
      <div style="width:30%;">
        <h1>환율 계산</h1>
        <ul>
          <li>
            <h2>송금 국가</h2>
            <span>미국(USD)</span>
          </li>

          <li>
            <h2>수취 국가</h2>
            <select class="selectbox" v-model="quoteCurrency" @change="getExchangeRate()">
              <option slot="first" disabled>선택하세요</option>
              <option value="KRW">한국(KRW)</option>
              <option value="JPY">일본(JPY)</option>
              <option value="PHP">필리핀(PHP)</option>
            </select>
          </li>
          <li>
            <h2>환율</h2>
            <span>{{exchangeRate}}</span>
          </li>
          <li>
            <h2>송금액</h2>
            <input type="number" v-model="amount" :rules="nameRules"><span>USD</span>
          </li>
        </ul>
        <button class="btn" :disabled="!valid" @click="getRemittance()" @keypress="getRemittance()">submit</button>
        <div class="result" v-if="remittance">수취금액은 <span>{{remittance}}</span> 입니다.</div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'Home',
    data() {
      return {
        items: [{value: 'KRW', text: '한국(KRW)'}, {value: 'JPY', text: '일본(JPY)'}, {value: 'PHP', text: '필리핀(PHP)'}],
        exchangeRate: '',
        quoteCurrency: '',
        amount: '',
        remittance: '',
        email: '',
        errors: [],
        valid: true,
        nameRules: [
          v => !!v || '이름을 입력하세요'],
      }
    },
    methods: {
      getExchangeRate() {
        let quoteCurrency = this.quoteCurrency;
        this.$http.get('/api/exchange-rate?quoteCurrency=' + quoteCurrency)
          .then(response => {
            console.log(response);
            // API를 통해 응답받은 환율 정보를 숫자 표기 요구사항에 맞게 처리하고 리턴
            this.exchangeRate = Number(response.data.toFixed(2)).toLocaleString('en') + ' ' + quoteCurrency;
          }, (err) => {
            alert("죄송합니다. 서버에 문제가 발생했습니다.")
          });
        // .catch(error => {
        //   console.log(error);
        // })
      },


      getRemittance() {
        let amount = this.amount;
        if (amount.length == 0) {
          alert("송금액을 입력해주세요");
          return;
        } else if (amount < 0 || amount > 10000) {
          alert("송금액이 범위를 벗어났습니다( 0<=송금액<=10000 )");
          return;
        }
        let quoteCurrency = this.quoteCurrency;
        if (quoteCurrency.length == 0) {
          alert("수취 국가를 선택해주세요");
          return;
        }
        this.$http.get('/api/remittance?quoteCurrency=' + quoteCurrency + '&amount=' + amount)
          .then(response => {
            // API를 통해 응답받은 수취 금액을 숫자 표기 요구사항에 맞게 처리하고 리턴
            this.remittance = Number(response.data.toFixed(2)).toLocaleString('en') + " " + quoteCurrency;
          })
          .catch(error => {
            console.log(error);
            alert("죄송합니다. 서버에 문제가 발생했습니다.")
          })
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
  }

  template {
    width: 400px;
    padding: 10px;
    margin: 0 auto;
  }

  ul {
    list-style: none;
    border-top: 2px solid #ddd;
    border-bottom: 2px solid #ddd;
    padding: 10px 0;
  }

  li {
    margin-bottom: 10px;
    width: 100%;
  }

  h1 {
    font-size: 21px;
    text-align: center;
    margin: 20px 0 30px;
  }

  h2 {
    font-size: 18px;
    font-weight: normal;
    margin-top: 10px;
    margin-bottom: 5px;
    display: inline-block;
    width: 80px;
    font-weight: bold;
  }

  .selectbox {
    width: 200px;
    padding: 5px;
    border-radius: 3px;
    font-size: 15px;
    border: 1px solid #aaaaaa;
  }

  input {
    border: 1px solid #ddd;
    padding: 5px;
    border-radius: 3px;
    margin-right: 5px;
    text-align: right;
  }

  .btn {
    border: none;
    box-shadow: none;
    border-radius: 3px;
    background: #000;
    padding: 10px;
    color: #fff;
    margin: 10px 0;
    width: 80px;
  }

  span {
    color: rgb(24, 41, 116);
    font-size: 16px;
  }

  .result {
    border-radius: 3px;
    padding: 10px 0px;
    font-size: 16px;
    text-align: center;
    color: rgb(24, 41, 116)
  }

</style>
