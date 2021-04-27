$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event) {
	const button = $(event.relatedTarget);

	const titleCode = button.data('code');
	const descricao = button.data('description');


	const modal = $(this);
	const form = modal.find('form');
	let action = form.data('url-base');

	if (!action.endsWith('/')) {
		action += '/'
	}

	form.attr('action', `${action}${titleCode}`);

	modal.find('div.modal-body span').html(`<div>Tem certeza que deseja excluir a cobrança: <strong>${descricao}</strong></div>`)
})

// executa quando a página carregar onLoad
$(function() {
	$('[rel="tooltip"]').tooltip();

	$('.js-currency').maskMoney({
		decimal: ',',
		thousands: '.',
		allowZero: true,
	})

	$('.js-atualizar-status').on('click', function(ev) {
		ev.preventDefault();

		const btnReceber = $(this);
		const urlReceber = btnReceber.attr('href');

		fetch(urlReceber, {
			method: 'PUT'
		})
			.then((success) => {
				return success.text();
			})
			.then((body) => {
				const codeCobranca = btnReceber.data('code-receber');
				$(`[data-role=${codeCobranca}]`).html(
					`<span class="px-2 py-1 badge badge-success"> ${body} </span>`
				)

				btnReceber.hide()
			})
			.catch((error) => {
				console.log(error)
			});
	})
})
