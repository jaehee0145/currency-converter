<template>

  <div class="hello">

    <h1>환율계산</h1>

    <ul>
      <li>
        <h2>송금 국가</h2>
        <select>
          <option>미국(USD)</option>
        </select>
      </li>
      <li>
        <h2>수취 국가</h2>
        <select v-model="quoteCurrency" @change="getExchangeRate()">
          <option slot="first" value="" disabled>선택하세요</option>
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
    <div class="result">수취금액은
      <div class type="number" id="remittance">{{remittance}}</div>
      입니다.
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
        remittance: ''
      }
    },
    methods: {
      test() {
        this.$http.get('/api/test').then(response => console.log(response))
      },
      getExchangeRate() {
        let quoteCurrency = this.quoteCurrency;
        this.$http.get('/api/exchange-rate?quoteCurrency=' + quoteCurrency)
          .then(response => {
            console.log(response);
            this.exchangeRate = Number(response.data.toFixed(2)).toLocaleString('en') + ' ' + quoteCurrency;
          })
          .catch(error => {
            console.log(error);
          })
      },

      getRemittance() {
        let amount = this.amount;
        let quoteCurrency = this.quoteCurrency;
        this.$http.get('/api/remittance?quoteCurrency=' + quoteCurrency + '&amount=' + amount)
          .then(response => {
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
    width: 100%;
  }

  .result {
    border-radius: 3px;
    padding: 10px 0px;
    color: #888;
    font-size: 18px;
    text-align: center;
  }
</style>
