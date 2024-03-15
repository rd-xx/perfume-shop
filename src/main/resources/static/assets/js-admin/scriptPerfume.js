$(document).ready(function () {
    $("#fileUploader").uploadFile({
        url: "/uploadFile",
        fileName: "file",
        showPreview: true,
        previewHeight: "100px",
        previewWidth: "100px",
        onSuccess: (files, data, xhr, pd) => {
            console.log(files)
            console.log(data)

            // sauvegarder la photo dans la base de donnée
            $.post("/api/images/new", {name: files[0], url: data[0]}, (datas) => {
                // la photo enregistre dans la base de donnée avec id
                console.log(datas)
                $.post(`/api/images/perfume/link`, {perfumeId: $("#perfumeId").val(), imageId: datas.id})
            })
        },
        onLoad: function (obj) {
            $.get(`/api/perfumes/${$("#perfumeId").val()}/images`, null, (data) => {
                for (let i = 0; i < data.length; i++) {
                    obj.createProgress(data[i]["name"], '/file/display' + data[i]["url"], "");
                }
            })
        }
    });
});
