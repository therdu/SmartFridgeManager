import { createVuetify } from 'vuetify'
import { aliases, mdi } from 'vuetify/iconsets/mdi'
import { VDataTable, VDataTableServer, VDataTableVirtual } from "vuetify/labs/VDataTable";

export default createVuetify({
  icons: {
    defaultSet: 'mdi',
    aliases,
    sets: {
      mdi,
    },
  }, components: {
    VDataTable,
    VDataTableServer,
    VDataTableVirtual,
  },
})
