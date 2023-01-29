import Vue from "vue";
import Vuetify from "vuetify/lib/framework";

Vue.use(Vuetify);

export default new Vuetify({
	breakpoint: {
		mobileBreakpoints: "xs",
	},
	theme: {
		themes: {
			light: {
				primary: "#00BCD4",
			},
		},
	},
});
