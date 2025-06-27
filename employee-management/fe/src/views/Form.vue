<template>
  <div class="container my-4">
    <h2 class="mb-4"><center>Form Data Karyawan</center></h2>

    <div class="card">
      <div class="card-header border border-1 border-black">
        <h5 class="card-title">{{ isEdit ? 'Edit Data Karyawan' : 'Tambah Data Karyawan' }}</h5>
      </div>
      <div class="card-body border border-1 border-black">
        <form @submit.prevent="confirmSave">
          <div class="row mb-3">
            <label class="col-sm-3 col-form-label">Nama</label>
            <div class="col-sm-9">
            <input v-model="form.name" type="text" class="form-control border-dark" required />
            </div>
          </div>

          <div class="row mb-3">
            <label class="col-sm-3 col-form-label">Tanggal Lahir</label>
            <div class="col-sm-9">
              <input v-model="form.birthDate" type="date" class="form-control border-dark" required />
            </div>
          </div>

          <div class="row mb-3">
            <label class="col-sm-3 col-form-label">Jabatan</label>
            <div class="col-sm-9">
              <select v-model="form.positionId" class="form-select border-dark" required>
                <option value="">Pilih Jabatan</option>
                <option v-for="pos in positions" :key="pos.id" :value="pos.id">
                {{ pos.name }}
                </option>
              </select>
            </div>
          </div>

          <div class="row mb-3">
            <label class="col-sm-3 col-form-label">NIP</label>
            <div class="col-sm-9">
              <input
              v-model="form.idNumber"
              type="text"
              class="form-control border-dark"
              :class="{ 'is-invalid': !/^\d+$/.test(form.idNumber) && form.idNumber }"
              required
              />
              <div class="invalid-feedback">
                Input harus berupa angka
              </div>
            </div>
          </div>

          <div class="row mb-3 align-items-center">
            <label class="col-sm-3 col-form-label">Jenis Kelamin</label>
            <div class="col-sm-9">
              <div class="form-check form-check-inline">
                <input class="form-check-input border-dark" type="radio" id="gender1" value="1" v-model="form.gender" required />
                <label class="form-check-label" for="gender1">Pria</label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input border-dark" type="radio" id="gender2" value="2" v-model="form.gender" required />
                <label class="form-check-label" for="gender2">Wanita</label>
              </div>
            </div>
          </div>

          <center>
            <router-link to="/" class="btn btn-outline-dark mx-2">Kembali</router-link>
            <button type="submit" class="btn btn-outline-dark mx-2">Simpan</button>
          </center>
          
        </form>
      </div>
      <div class="card-footer border border-1 border-black"></div>
    </div>

    <!-- Modal Konfirmasi -->
    <div class="modal fade" id="confirmSaveModal" tabindex="-1">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header bg-black text-white">
            <h5 class="modal-title">Konfirmasi Simpan</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
          </div>
          <div class="modal-body">Apakah kamu yakin ingin menyimpan data ini?</div>
          <div class="modal-footer">
            <button type="button" class="btn btn-outline-dark" data-bs-dismiss="modal">Batal</button>
            <button type="button" class="btn btn-outline-dark" @click="submitForm">Simpan</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import api from '../api'
import * as bootstrap from 'bootstrap'

const route = useRoute()
const router = useRouter()

const isEdit = ref(false)
const positions = ref([])
const modalInstance = ref(null)

const form = ref({
  id: null,
  name: '',
  birthDate: '',
  positionId: '',
  idNumber: '',
  gender: '',
})

onMounted(async () => {
  const modalEl = document.getElementById('confirmSaveModal')
  modalInstance.value = new bootstrap.Modal(modalEl)

  await getPositions()

  if (route.params.id) {
    isEdit.value = true
    const res = await api.get(`/employees/${route.params.id}`)
    const emp = res.data
    form.value = {
      id: emp.id,
      name: emp.name,
      birthDate: emp.birthDate,
      positionId: emp.position.id,
      idNumber: emp.idNumber,
      gender: emp.gender,
    }
  }
})

const getPositions = async () => {
  const res = await api.get('/positions')
  positions.value = res.data
}

const confirmSave = () => {
  modalInstance.value.show()
}

const submitForm = async () => {
  // 🔧 Susun payload yang sesuai struktur backend
  const payload = {
    ...form.value,
    position: { id: form.value.positionId }
  }
  delete payload.positionId

  try {
    if (isEdit.value) {
      await api.put(`/employees/${form.value.id}`, payload)
    } else {
      await api.post('/employees', payload)
    }
    modalInstance.value.hide()
    router.push('/')
  } catch (err) {
    console.error('Gagal simpan:', err)
    alert('Gagal menyimpan data')
  }
}
</script>

<style scoped>
.form-check-input:checked {
  background-color: black;
  border-color: black;
  box-shadow: none;
}

.form-control:focus,
.form-select:focus,
.form-check-input:focus {
  border-color: black;
  box-shadow: 0 0 0 0.2rem rgba(0, 0, 0, 0.25);
}
</style>

