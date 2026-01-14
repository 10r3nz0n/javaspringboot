document.addEventListener("DOMContentLoaded", function () {
    var form = document.getElementById("alunoForm");
    if (!form) {
        return;
    }

    var cpfInput = document.getElementById("cpf");
    var idadeInput = document.getElementById("idade");
    var erro = document.getElementById("erroCliente");

    function limparMensagem() {
        if (erro) {
            erro.textContent = "";
        }
    }

    function mostrarMensagem(msg) {
        if (erro) {
            erro.textContent = msg;
        }
    }

    function normalizarCpf(valor) {
        if (!valor) {
            return "";
        }
        return valor.replace(/\D/g, "");
    }

    if (cpfInput) {
        cpfInput.addEventListener("input", function () {
            limparMensagem();
            cpfInput.value = normalizarCpf(cpfInput.value);
        });
    }

    form.addEventListener("submit", function (event) {
        limparMensagem();

        var cpf = cpfInput ? normalizarCpf(cpfInput.value) : "";
        var idade = idadeInput ? Number(idadeInput.value) : NaN;

        if (!cpf || cpf.length !== 11) {
            event.preventDefault();
            mostrarMensagem("Informe um CPF com 11 digitos.");
            return;
        }

        if (!Number.isFinite(idade) || idade < 0) {
            event.preventDefault();
            mostrarMensagem("Informe uma idade valida.");
            return;
        }
    });
});
