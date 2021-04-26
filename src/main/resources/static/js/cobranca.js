$('#confirmacaoExclusaoModal').on('show.bs.modal', function (event) {
	const button = $(event.relatedTarget);
	
	const titleCode = button.data('code');
	const descricao = button.data('description');
	
	
	const modal = $(this);
	const form = modal.find('form');
	let action = form.attr('action');
	
	const regex = /\/\w+/
	
	action = action.match(regex)
		
	form.attr('action', `${action}/${titleCode}`);
	
	modal.find('div.modal-body span').html(`<div>Tem certeza que deseja excluir a cobrança: <strong>${descricao}</strong></div>`)
})