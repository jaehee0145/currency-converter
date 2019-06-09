<template>

  <div class="hello">

    <h1>환율 계산</h1>

    <ul>

      <li>
        <h2>송금 국가</h2>
        <span> 미국(USD) </span>
      </li>

      <li>
        <h2>수취국가</h2>
        <select v-model="quoteCurrency" @change="getExchangeRate()">
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
        <input type="number" v-model="amount"><span>USD</span>
      </li>

    </ul>

    <button @click="getRemittance()">submit</button>

    <div class="result" v-if="remittance">수취금액은
      <div class type="number" id="remittance">{{remittance}}</div>
      입니다.
    </div>

    <div v-if="errors.length">
      <ul>
        <li v-for="error in errors" :key="error.id">{{ error }}</li>
      </ul>
    </div>

  </div>
</template>

<script>
  export default {
    name: 'Home',
    data() {
      return {
        exchangeRate: '',
        quoteCurrency: '',
        amount: '',
        remittance: '',
        email: '',
        errors: []
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
              alert("문제가 발생했습니다.")
          });
        // .catch(error => {
        //   console.log(error);
        // })
      },


      getRemittance() {
        let amount = this.amount;
        if (amount < 0 || amount > 10000) {
          alert("송금액이 바르지 않습니다.");
          return;
        }
        let quoteCurrency = this.quoteCurrency;
        this.$http.get('/api/remittance?quoteCurrency=' + quoteCurrency + '&amount=' + amount)
          .then(response => {
            // API를 통해 응답받은 수취 금액을 숫자 표기 요구사항에 맞게 처리하고 리턴
            this.remittance = Number(response.data.toFixed(2)).toLocaleString('en') + " " + quoteCurrency;
          })
          .catch(error => {
            console.log(error);
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

  body {
    width: 400px;
    padding: 10px;
    margin: 0 auto;
  }

  ul {
    list-style: none;
    border-top: 1px solid #ddd;
    border-bottom: 1px solid #ddd;
    padding: 10px 0;
  }

  h1 {
    font-size: 20px;
    text-align: center;
    margin: 20px 0 30px;
  }

  h2 {
    font-size: 15px;
    font-weight: normal;
    margin-top: 10px;
    margin-bottom: 5px;
    display: inline-block;
    width: 80px;
  }

  select {
    width: 290px;
    padding: 5px;
    border-radius: 3px;
  }

  input {
    border: 1px solid #ddd;
    padding: 5px;
    border-radius: 3px;
    margin-right: 5px;
    text-align: right;
  }

  button {
    border: none;
    box-shadow: none;
    border-radius: 3px;
    background: #000;
    padding: 10px;
    color: #fff;
    margin: 10px 0;
    width: 100px;
  }

  .result {
    border-radius: 3px;
    padding: 10px 0px;
    color: #888;
    font-size: 18px;
    text-align: center;
  }
</style>
