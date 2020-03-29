<template>
  <button-raised type="success" :click="doSpeak" :show="show" :disabled="started">
    <i :class="classIcon"></i>
    <slot></slot>
  </button-raised>
</template>
<script>
  import ButtonRaised from '../ButtonRaised';
  export default{
    props: ['text'],
    data(){
      const show = window.speechSynthesis && window.SpeechSynthesisUtterance;
      return {started: false, show};
    },
    methods: {
      doSpeak(){
        if (!this.text || this.text === '' || this.started) {
          console.log('do nothing');
          return;
        }

        this.started = true;
        const msg = new SpeechSynthesisUtterance(this.text);
        msg.onend = () => this.started = false;
        msg.lang = 'fr-FR';
        window.speechSynthesis.speak(msg);

      }
    },
    computed: {
      classIcon: function () {
        const {started} = this;
        return {fa: true,  'fa-comment': !started, 'fa-ban': started};
      }
    },
    components: {
      ButtonRaised
    }
  }
</script>
