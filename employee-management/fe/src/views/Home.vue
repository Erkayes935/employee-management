<template>
  <div class="container my-4">
    <h2 class="mb-4"><center>Master Karyawan</center></h2>

    

    <div class="container my-4">
      <div class="card">
        <div class="card-header border border-1 border-black">
          <h5 class="card-title">Daftar Karyawan</h5>
        </div>
        <div class="card-body border border-1 border-black">
          <router-link to="/add" class="btn btn-outline-dark mb-3">
            <i class="bi bi-plus-circle"></i> Tambah Data
          </router-link>
          <table class="table table-bordered table-striped">
            <thead class="table-dark">
              <tr>
                <th @click="toggleSort('id')" style="cursor: pointer">
                  <div class="d-flex align-items-center gap-1">
                    <span>No</span> 
                    <div class="d-flex flex-column justify-content-center" style="line-height: 0.1; font-size: 0.65rem;">
                      <i class="bi" :class="sortKey === 'id'? (sortAsc ? 'bi-caret-up-fill' : 'bi-caret-up') : 'bi-caret-up text-secondary'"></i>
                      <i class="bi" :class="sortKey === 'id'? (!sortAsc ? 'bi-caret-down-fill' : 'bi-caret-down') : 'bi-caret-down text-secondary'"></i>
                    </div>
                  </div>
                </th>
                <th @click="toggleSort('name')" style="cursor: pointer">
                  <div class="d-flex align-items-center gap-1">
                    <span>Nama</span>
                    <div class="d-flex flex-column justify-content-center" style="line-height: 0.1; font-size: 0.65rem;">
                      <i class="bi" :class="sortKey === 'name'? (sortAsc ? 'bi-caret-up-fill' : 'bi-caret-up') : 'bi-caret-up text-secondary'"></i>
                      <i class="bi" :class="sortKey === 'name'? (!sortAsc ? 'bi-caret-down-fill' : 'bi-caret-down') : 'bi-caret-down text-secondary'"></i>
                    </div>
                  </div>
                </th>
                <th @click="toggleSort('birthDate')" style="cursor: pointer">
                  <div class="d-flex align-items-center gap-1">
                    <span>Tanggal Lahir </span>
                    <div class="d-flex flex-column justify-content-center" style="line-height: 0.1; font-size: 0.65rem;">
                      <i class="bi" :class="sortKey === 'birthDate'? (sortAsc ? 'bi-caret-up-fill' : 'bi-caret-up') : 'bi-caret-up text-secondary'"></i>
                      <i class="bi" :class="sortKey === 'birthDate'? (!sortAsc ? 'bi-caret-down-fill' : 'bi-caret-down') : 'bi-caret-down text-secondary'"></i>
                    </div>
                  </div>
                </th>
                <th @click="toggleSort('position')" style="cursor: pointer">
                  <div class="d-flex align-items-center gap-1">
                    <span>Posisi</span>
                    <div class="d-flex flex-column justify-content-center" style="line-height: 0.1; font-size: 0.65rem;">
                      <i class="bi" :class="sortKey === 'position'? (sortAsc ? 'bi-caret-up-fill' : 'bi-caret-up') : 'bi-caret-up text-secondary'"></i>
                      <i class="bi" :class="sortKey === 'position'? (!sortAsc ? 'bi-caret-down-fill' : 'bi-caret-down') : 'bi-caret-down text-secondary'"></i>
                    </div>
                  </div>
                </th>
                <th @click="toggleSort('idNumber')" style="cursor: pointer">
                  <div class="d-flex align-items-center gap-1">
                    <span>NIP</span>
                    <div class="d-flex flex-column justify-content-center" style="line-height: 0.1; font-size: 0.65rem;">
                      <i class="bi" :class="sortKey === 'idNumber'? (sortAsc ? 'bi-caret-up-fill' : 'bi-caret-up') : 'bi-caret-up text-secondary'"></i>
                      <i class="bi" :class="sortKey === 'idNumber'? (!sortAsc ? 'bi-caret-down-fill' : 'bi-caret-down') : 'bi-caret-down text-secondary'"></i>
                    </div>
                  </div>
                </th>
                <th @click="toggleSort('gender')" style="cursor: pointer">
                  <div class="d-flex align-items-center gap-1">
                    <span>Jenis Kelamin</span>
                    <div class="d-flex flex-column justify-content-center" style="line-height: 0.1; font-size: 0.65rem;">
                      <i class="bi" :class="sortKey === 'gender'? (sortAsc ? 'bi-caret-up-fill' : 'bi-caret-up') : 'bi-caret-up text-secondary'"></i>
                      <i class="bi" :class="sortKey === 'gender'? (!sortAsc ? 'bi-caret-down-fill' : 'bi-caret-down') : 'bi-caret-down text-secondary'"></i>
                    </div>
                  </div>
                </th> 
                <th>Aksi</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="emp in sortedEmployees" :key="emp.id" v-if="employees.length > 0">
                <td>{{ emp.id }}</td>
                <td>{{ emp.name }}</td>
                <td>{{ formatDate(emp.birthDate) }}</td>
                <td>{{ emp.position?.name }}</td>
                <td>{{ emp.idNumber }}</td>
                <td>{{ emp.gender === 1 ? 'Pria' : 'Wanita' }}</td>
                <td class="text-center">
                  <router-link :to="`/edit/${emp.id}`" class="btn btn-dark btn-sm mx-1" title="Edit">
                    <i class="bi bi-pencil-square fs-8"></i>
                  </router-link>
                  <button class="btn p-0 border-0 bg-transparent text-dark" @click="openModal(emp.id)" title="Hapus">
                    <i class="bi bi-trash-fill fs-8"></i>
                  </button>
                </td>
              </tr>

              <tr v-else>
                  <td colspan="7" class="text-center text-muted">Tidak ada data karyawan.</td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="card-footer border border-1 border-black"></div>
      </div>
    </div>
    <div class="modal fade" id="confirmDeleteModal" tabindex="-1">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header bg-black text-white">
            <h5 class="modal-title">Konfirmasi Hapus</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            Apakah kamu yakin ingin menghapus data ini?
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-outline-dark" data-bs-dismiss="modal">Tidak</button>
            <button type="button" class="btn btn-outline-dark" @click="deleteEmployee">Ya</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import api from '../api'
import * as bootstrap from 'bootstrap'

const employees = ref([])
const selectedId = ref(null)
const modalInstance = ref(null)

const getEmployees = async () => {
  const res = await api.get('/employees')
  employees.value = res.data
}

onMounted(() => {
  getEmployees()
  const modalEl = document.getElementById('confirmDeleteModal')
  if (modalEl) {
    modalInstance.value = new bootstrap.Modal(modalEl)
  }
})

const openModal = (id) => {
  selectedId.value = id
  modalInstance.value.show()
}

const formatDate = (dateStr) => {
  if (!dateStr) return '-'
  const date = new Date(dateStr)
  const options = { day: 'numeric', month: 'long', year: 'numeric' }
  return date.toLocaleDateString('id-ID', options)
}

const deleteEmployee = async () => {
  await api.delete(`/employees/${selectedId.value}`)
  modalInstance.value.hide()
  getEmployees()
}
const sortKey = ref('name', 'id', 'birthDate', 'positionId', 'idNumber', 'gender')
const sortAsc = ref(true)

const sortedEmployees = computed(() => {
  return [...employees.value].sort((a, b) => {
    let valA, valB

    switch (sortKey.value) {
      case 'position':
        valA = a.position?.name || ''
        valB = b.position?.name || ''
        break
      case 'birthDate':
        valA = new Date(a.birthDate)
        valB = new Date(b.birthDate)
        break
      default:
        valA = a[sortKey.value] || ''
        valB = b[sortKey.value] || ''
    }

    if (valA < valB) return sortAsc.value ? -1 : 1
    if (valA > valB) return sortAsc.value ? 1 : -1
    return 0
  })
})


const toggleSort = (key) => {
  if (sortKey.value === key) {
    sortAsc.value = !sortAsc.value
  } else {
    sortKey.value = key
    sortAsc.value = true
  }
}

</script>
